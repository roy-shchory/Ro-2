package com.zook.zook;

import java.io.BufferedWriter;
import java.io.IOException;

import org.apache.zookeeper.ZooKeeper;

public class Modified_3PC {

	private ZooKeeper zooKeeper;
	private FailureDetector failureDetector;
	
	private int n;
	private int id;
	private boolean vote;
	private String crashTime;
	private int crashRound;

	private BufferedWriter out;

	public Modified_3PC(ZooKeeper zooKeeper, FailureDetector failureDetector, int n, int id, boolean vote, String crashTime, int crashRound, BufferedWriter out) {
		this.zooKeeper = zooKeeper;
		this.failureDetector = failureDetector;
		
		this.n = n;
		this.id = id;
		this.vote = vote;
		this.crashTime = crashTime;
		this.crashRound = crashRound;
		
		this.out = out;
	}

	public void start(int coordinatorId) throws CrashedException, IOException {
		// ***START 3PC_MODIFIED***
		// if 0-V, crash!
		if (crashRound == 0 && "V".equals(crashTime))
			CrashedException.crash(out);

		// send vote to coordinator (the transaction sender)

		// only coordinator - wait for votes or until new suspicion:

		// only coordinator - send results to all:

		// wait for coordinator results, or until the coordinator's suspicion:

		// ***START CONSENSUS (M&R)***
	}
}
