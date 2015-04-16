package com.zenglm.redis;

import java.util.Set;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.exceptions.JedisException;

import com.zenglm.dts.redis.JedisSentinelShardPool;
import com.zenglm.dts.redis.JedisUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class RedisTest {
	@Autowired
	private JedisSentinelShardPool pool;
	@Autowired
	private ShardedJedisPool shardedJedisPool;
	//@Test
	public void test() {
		Jedis jedis = JedisUtil.getInstance().getJedis("127.0.0.1", 6379);
		System.out.println(jedis);
		System.out.println(jedis.get("test"));
		jedis.set("test", "test1");
		System.out.println(jedis.get("test"));
		jedis.flushDB();
		jedis.rpush("list", "a");
		jedis.rpush("list", "b");
		jedis.rpush("list", "c");
		System.out.println(jedis.llen("list"));
		System.out.println(jedis.lindex("list", 0));
	}

	//@Test
	public void testSentinel() {
		Jedis jedis = null;
		try {
			jedis = pool.getWriteSource();
			jedis.set("test", "test1");
			jedis.zadd("zset", 1, "aaa");
			jedis.zadd("zset", 2, "aaa");
			jedis.zrange("zset", 0, 1);

			System.out.println(jedis.get("foo"));
			ShardedJedis sj = pool.getReadSource();
			System.out.println(sj.get("foo"));
			System.out.println(sj.get("test"));
			try {
				Thread.sleep(1000000000000l);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (JedisException je) {
			throw je;
		} finally {
			if (jedis != null)
				pool.returnResource(jedis);
		}

	}

	//@Test
	public void testZSet() {
		Jedis jedis = pool.getWriteSource();
		jedis.zadd("zset", 11, "aaa1");
		jedis.zadd("zset", 10, "aaa");
		jedis.zadd("zset", 12, "aaa2");
		jedis.zadd("zset", 13, "aaa3");
		jedis.zadd("zset", 14, "aaa4");
		//jedis.del("zset");
		System.out.println(jedis.randomKey());
		System.out.println(jedis.zscore("zset", "aaa"));
		System.out.println(jedis.zscore("zset", "aaa11"));
		Long score = jedis.zrank("zset", "aaa");
		System.out.println(score);
		Set<String> set = jedis.zrange("zset", score, score);
		System.out.println(set);
		set = jedis.zrevrange("zset", 0, 2);
		System.out.println(set);
		Set<Tuple> elements = jedis.zrangeWithScores("zset", 0, -1);
		for (Tuple tuple : elements) {
			System.out.println(tuple.getElement() + "-"
					+ (tuple.getScore() == 10l));
		}
	}

}
