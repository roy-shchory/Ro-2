package com.zook.zook;

import java.io.IOException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooKeeper.States;

public class ZkConnector {
	ZooKeeper zookeeper;
	java.util.concurrent.CountDownLatch connectedSignal = new java.util.concurrent.CountDownLatch(1);

	public void connect(String host) throws IOException, InterruptedException {
		zookeeper = new ZooKeeper(host, 5000, new Watcher() {
			public void process(WatchedEvent e) {
				if (e.getState() == KeeperState.SyncConnected)
					connectedSignal.countDown();
			}
		});
		connectedSignal.await();
	}

	public void close() throws InterruptedException {
		zookeeper.close();
	}

	public ZooKeeper getZooKeeper() {
		if (zookeeper == null || !zookeeper.getState().equals(States.CONNECTED))
			throw new IllegalStateException("ZooKeeper is not connected.");
		return zookeeper;
	}
}
