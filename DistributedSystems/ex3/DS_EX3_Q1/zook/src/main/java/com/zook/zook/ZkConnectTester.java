package com.zook.zook;

import java.io.IOException;
import java.util.concurrent.Semaphore;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class ZkConnectTester {
	public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
		
		
		
		
		
		
//		// final Semaphore sem1 = new Semaphore(0, true);
//
//		final Object i = new Object();
//		final Object j = new Object();
//
//		System.out.println("> first");
//		new Thread(() -> {
//			try {
//				Thread.sleep(1000);
//
//				System.out.println("# first");
//				synchronized (i) {
//					i.notify();
//				}
//				Thread.sleep(1000);
//				synchronized (j) {
//					j.notify();
//				}
//				System.out.println("# last");
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}).start();
//
//		// sem1.acquire();
//		synchronized (j) {
//			synchronized (i) {
//				i.wait();
//				j.wait();
//			}
//		}
//		System.out.println("> last");

		// ZooKeeper zk;
		// ZkConnector zkc = new ZkConnector();
		//
		// zkc.connect("localhost");
		// zk = zkc.getZooKeeper();
		// //zk.create("/newznode", "new znode".getBytes(), Ids.OPEN_ACL_UNSAFE,
		// CreateMode.PERSISTENT);
		//
		// zk.getChildren("/", new Watcher() {
		//
		// public void process(WatchedEvent event) {
		// System.out.println(event.getPath());
		// }
		// });
		//
		//
		//
		// while (true) {
		//
		// }

		// zk.close();
	}
}
