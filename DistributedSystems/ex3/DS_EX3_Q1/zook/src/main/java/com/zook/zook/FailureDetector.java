package com.zook.zook;

import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class FailureDetector {
	private static final String PATH = ZkConnector.EX_ROOT + "/failure_detector"; 
	
	private ZooKeeper zk;
	private boolean[] nodesLiveness;
	
	public FailureDetector(int size, int id) {
		
	}
}
