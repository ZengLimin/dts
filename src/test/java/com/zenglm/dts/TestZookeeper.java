/*package com.zenglm.dts;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

public class TestZookeeper {

	@Test
	public void test() throws IOException, KeeperException,
			InterruptedException {
		ZooKeeper zk = new ZooKeeper("127.0.0.1:2181", 500000, new Watcher() {
			// 监控所有被触发的事件
			public void process(WatchedEvent event) {
				System.out.println("*************");
			}
		});

		//创建一个节点root，数据是mydata,不进行ACL权限控制，节点为永久性的(即客户端shutdown了也不会消失)
		zk.create("/root", "mydata".getBytes(), Ids.OPEN_ACL_UNSAFE,
				CreateMode.PERSISTENT);

	}

}
*/