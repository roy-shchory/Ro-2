package com.zook.zook;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
		//init params:
		int n = Integer.parseInt(args[0]);
		int id = Integer.parseInt(args[1]);
		
		boolean vote = "yes".equals(args[2]);
		
		String crashTime = args[3].split("-")[1];
		int crashRound = Integer.parseInt(args[3].split("-")[0]);
		
		BufferedWriter out = new BufferedWriter(new FileWriter(new File("nbac_output_" + id + ".txt")));
		
		//init zookeeper:
		ZooKeeper zooKeeper = new ZkConnector().connect().getZooKeeper();
		
		//init failure detector:
		FailureDetector fd = new FailureDetector(n, id);
		
		//enter barrier:
		new Barrier(zooKeeper, n, id).enter();
		
		//lowest id (that didn't crash) sends the transaction to all:
		
		//***START 3PC_MODIFIED***
		//if V, crash!
		
		//send vote to coordinator (the transaction sender)
		
		//only coordinator - wait for votes or until new suspicion:
		
		//only coordinator - send results to all:
		
		//wait for coordinator results, or until the coordinator's suspicion:
		
		//***START CONSENSUS (M&R)***
	}

}
