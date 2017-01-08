package com.zook.zook;

import java.io.BufferedWriter;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooKeeper;

public class Consensus {
	private ZooKeeper zooKeeper;
	private FailureDetector failureDetector;

	private int n;
	private int id;
	private boolean vote;
	private String crashTime;
	private int crashRound;

	private BufferedWriter out;
	
	public Consensus(ZooKeeper zooKeeper, FailureDetector failureDetector, int n, int id,
			String crashTime, int crashRound, BufferedWriter out) {
		this.zooKeeper = zooKeeper;
		this.failureDetector = failureDetector;

		this.n = n;
		this.id = id;
		this.vote = vote;
		this.crashTime = crashTime;
		this.crashRound = crashRound;

		this.out = out;

		ZooHelper.createNewNode(zooKeeper, ZooHelper.CONSENSUS_ROOT, new byte[0], CreateMode.PERSISTENT);
	}

	public void start(boolean startValue) {
		
	}
}
