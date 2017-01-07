package com.zook.zook;

import java.io.IOException;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class ZkConnectTester {
	public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
		ZooKeeper zk;
		ZkConnector zkc = new ZkConnector();
		
		zkc.connect("localhost");
		zk = zkc.getZooKeeper();
		//zk.create("/newznode", "new znode".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		
		zk.getChildren("/", new Watcher() {
			
			public void process(WatchedEvent event) {
				System.out.println(event.getPath());
			}
		});
		
		
		
		while (true) {
			
		}
		
		//zk.close();
	}
}
