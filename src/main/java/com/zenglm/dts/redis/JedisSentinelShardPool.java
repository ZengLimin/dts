package com.zenglm.dts.redis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * 支持读写分离和容灾，使用Sentinel监控redis集群的主备状态，master写入数据，slave读取数据，如果没有slave读写都用master，
 * 集群中发生宕机，会自动更新读连接,支持多个slave
 * 
 * @author zeng_limin@163.com
 * @since 2015年2月4日 上午10:52:54
 *
 */
public class JedisSentinelShardPool extends JedisSentinelPool {

	private ShardedJedisPool shardedJedisPool;

	private Set<SlaveListener> slaveListeners = new HashSet<SlaveListener>();

	private Set<String> sentinels;

	public JedisSentinelShardPool(String masterName, Set<String> sentinels,
			final GenericObjectPoolConfig poolConfig) {
		this(masterName, sentinels, poolConfig, Protocol.DEFAULT_TIMEOUT, null,
				Protocol.DEFAULT_DATABASE);
	}

	public JedisSentinelShardPool(String masterName, Set<String> sentinels) {
		this(masterName, sentinels, new GenericObjectPoolConfig(),
				Protocol.DEFAULT_TIMEOUT, null, Protocol.DEFAULT_DATABASE);
	}

	public JedisSentinelShardPool(String masterName, Set<String> sentinels,
			String password) {
		this(masterName, sentinels, new GenericObjectPoolConfig(),
				Protocol.DEFAULT_TIMEOUT, password);
	}

	public JedisSentinelShardPool(String masterName, Set<String> sentinels,
			final GenericObjectPoolConfig poolConfig, int timeout,
			final String password) {
		this(masterName, sentinels, poolConfig, timeout, password,
				Protocol.DEFAULT_DATABASE);
	}

	public JedisSentinelShardPool(String masterName, Set<String> sentinels,
			final GenericObjectPoolConfig poolConfig, final int timeout) {
		this(masterName, sentinels, poolConfig, timeout, null,
				Protocol.DEFAULT_DATABASE);
	}

	public JedisSentinelShardPool(String masterName, Set<String> sentinels,
			final GenericObjectPoolConfig poolConfig, final String password) {
		this(masterName, sentinels, poolConfig, Protocol.DEFAULT_TIMEOUT,
				password);
	}

	public JedisSentinelShardPool(String masterName, Set<String> sentinels,
			final GenericObjectPoolConfig poolConfig, int timeout,
			final String password, final int database) {
		super(masterName, sentinels, poolConfig, timeout, password, database);
		this.sentinels = sentinels;
		initShardedJedisPool(masterName);
		listeners(masterName);
	}

	public Jedis getWriteSource() {
		return this.getResource();
	}

	public ShardedJedis getReadSource() {
		return shardedJedisPool.getResource();
	}

	public void destroy() {
		for (SlaveListener s : slaveListeners) {
			s.shutdown();
		}
		super.destroy();
	}

	private void initShardedJedisPool(final String masterName) {
		for (String sentinel : sentinels) {
			final HostAndPort hap = toHostAndPort(Arrays.asList(sentinel
					.split(":")));
			log.fine("Connecting to Sentinel " + hap);
			Jedis jedis = null;
			try {
				jedis = new Jedis(hap.getHost(), hap.getPort());

				//查询所有的slave
				List<Map<String, String>> slaveHost = jedis
						.sentinelSlaves(masterName);
				List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
				JedisShardInfo master = null;
				for (Map<String, String> map : slaveHost) {
					if ("slave".equals(map.get("flags"))
							&& "ok".equals(map.get("master-link-status"))) {
						shards.add(new JedisShardInfo(map.get("ip"), Integer
								.valueOf(map.get("port"))));
					}
					if (master == null) {
						master = new JedisShardInfo(map.get("master-host"),
								map.get("master-port"));
					}
				}
				//如果未查询到slave则使用master
				if (shards.isEmpty()) {
					log.warning("no slave!");
					shards.add(master);
				}
				shardedJedisPool = new ShardedJedisPool(poolConfig, shards);
				break;
			} catch (JedisConnectionException e) {
				log.warning("Cannot connect to sentinel running @ " + hap
						+ ". Trying next one.");
			} finally {
				if (jedis != null) {
					jedis.close();
				}
			}
		}
	}

	private void listeners(final String masterName) {
		for (String sentinel : sentinels) {
			final HostAndPort hap = toHostAndPort(Arrays.asList(sentinel
					.split(":")));
			SlaveListener slaveListener = new SlaveListener(masterName,
					hap.getHost(), hap.getPort());
			slaveListeners.add(slaveListener);
			slaveListener.start();
		}
	}

	private HostAndPort toHostAndPort(List<String> getMasterAddrByNameResult) {
		String host = getMasterAddrByNameResult.get(0);
		int port = Integer.parseInt(getMasterAddrByNameResult.get(1));
		return new HostAndPort(host, port);
	}

	class SlaveListener extends Thread {

		private String masterName;
		private String host;
		private int port;
		private long subscribeRetryWaitTimeMillis = 5000;
		private Jedis j;
		private AtomicBoolean running = new AtomicBoolean(false);

		public SlaveListener() {
		}

		public SlaveListener(String masterName, String host, int port) {
			this.masterName = masterName;
			this.host = host;
			this.port = port;
		}

		public SlaveListener(String masterName, String host, int port,
				long subscribeRetryWaitTimeMillis) {
			this(masterName, host, port);
			this.subscribeRetryWaitTimeMillis = subscribeRetryWaitTimeMillis;
		}

		public void run() {
			running.set(true);
			while (running.get()) {
				j = new Jedis(host, port);
				try {
					j.subscribe(new JedisPubSub() {
						@Override
						public void onMessage(String channel, String message) {
							log.fine("Sentinel " + host + ":" + port
									+ " published: " + message + ".");
							initShardedJedisPool(masterName);
						}
					}, "+switch-master", "+slave", "+sdown", "-sdown",
							"+odown", "-odown");

				} catch (JedisConnectionException e) {
					if (running.get()) {
						log.severe("Lost connection to Sentinel at " + host
								+ ":" + port + ". Sleeping "
								+ subscribeRetryWaitTimeMillis
								+ "ms and retrying.");
						try {
							Thread.sleep(subscribeRetryWaitTimeMillis);
						} catch (InterruptedException e1) {
							log.warning(e1.getMessage());
						}
					} else {
						log.fine("Unsubscribing from Sentinel at " + host + ":"
								+ port);
					}
				}
			}
		}

		public void shutdown() {
			try {
				log.fine("Shutting down listener on " + host + ":" + port);
				running.set(false);
				// This isn't good, the Jedis object is not thread safe
				j.disconnect();
			} catch (Exception e) {
				log.severe("Caught exception while shutting down: "
						+ e.getMessage());
			}
		}
	}

}
