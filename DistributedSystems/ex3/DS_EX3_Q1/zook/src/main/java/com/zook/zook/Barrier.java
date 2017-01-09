package com.zook.zook;

import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

/**
 * Barrier class
 * from: https://zookeeper.apache.org/doc/trunk/zookeeperTutorial.html
 */
public class Barrier implements Watcher {
	private static final String PATH = ZooHelper.BARRIER_ROOT;
	
	private ZooKeeper zk;
	private Integer mutex = Integer.valueOf(-1);

	private int id;
	private int size;	

	/**
	 * Barrier constructor
	 *
	 * @param address
	 * @param root
	 * @param size
	 */
	Barrier(ZooKeeper zk, int size, int id) {
		this.zk = zk;
		this.id = id;
		this.size = size;
		
		// Create barrier node
		ZooHelper.createNewNode(zk, PATH, new byte[0], CreateMode.PERSISTENT);
	}

	/**
	 * Join barrier
	 *
	 * @return
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	boolean enter() throws KeeperException, InterruptedException {
		zk.create(PATH + "/" + id, new byte[0], Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		while (true)
			synchronized (mutex) {
				List<String> list = zk.getChildren(PATH, this);
				if (list.size() >= size)
					return true;
				mutex.wait();
			}
	}

	public synchronized void process(WatchedEvent __) {
		synchronized (mutex) {
			mutex.notify();
		}
	}
}
