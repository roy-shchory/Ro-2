package com.zook.zook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooKeeper.States;

public class ZkConnector {
	ZooKeeper zookeeper;
	java.util.concurrent.CountDownLatch connectedSignal = new java.util.concurrent.CountDownLatch(1);

	public ZkConnector connect() throws IOException, InterruptedException, KeeperException {
		return connect("localhost");
	}

	public ZkConnector connect(String host) throws IOException, InterruptedException, KeeperException {
		zookeeper = new ZooKeeper(host, 5000, new Watcher() {
			public void process(WatchedEvent e) {
				if (e.getState() == KeeperState.SyncConnected)
					connectedSignal.countDown();
			}
		});
		connectedSignal.await();

		init_and_clean();

		return this;
	}

	public void close() throws InterruptedException {
		zookeeper.close();
	}

	public ZooKeeper getZooKeeper() {
		if (zookeeper == null || !zookeeper.getState().equals(States.CONNECTED))
			throw new IllegalStateException("ZooKeeper is not connected.");
		return zookeeper;
	}

	private void init_and_clean() throws KeeperException, InterruptedException {
		//if (zookeeper.exists(ZooHelper.EX_ROOT, false) != null)
			//recursiveDeleteNode(ZooHelper.EX_ROOT);
		ZooHelper.createNewNode(zookeeper, ZooHelper.EX_ROOT, new byte[0], CreateMode.PERSISTENT);
	}

	private void recursiveDeleteNode(String path) {
		List<String> children = new ArrayList<>();
		try {
			children = zookeeper.getChildren(path, false);

			for (String child : children)
				recursiveDeleteNode(path + "/" + child);
			zookeeper.delete(path, -1);
		} catch (InterruptedException | KeeperException e) {
		}
	}
}
