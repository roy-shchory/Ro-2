package com.zook.zook;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.Semaphore;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException, KeeperException, CrashedException {
		// init params:
		int n = Integer.parseInt(args[0]);
		int id = Integer.parseInt(args[1]);

		boolean vote = "yes".equals(args[2]);

		String crashTime = args[3].split("-")[1];
		int crashRound = Integer.parseInt(args[3].split("-")[0]);

		System.out.println(">> Starting node " + id + " from " + n + " nodes. Vote: " + vote + ". Crash: " + crashRound
				+ "-" + crashTime);

		try (BufferedWriter out = new BufferedWriter(new FileWriter(new File("nbac_output_" + id + ".txt")))) {
			// init zookeeper:
			ZooKeeper zooKeeper = new ZkConnector().connect().getZooKeeper();
			System.out.println(">> ZooKeeper connected");

			// init failure detector:
			FailureDetector fd = new FailureDetector(zooKeeper, n, id);
			System.out.println(">> Failure detector started");

			// enter barrier:
			System.out.println(">> Waiting for other nodes on barrier...");
			new Barrier(zooKeeper, n, id).enter();
			System.out.println(">> Exited barrier");

			// lowest id (that didn't crash) sends the transaction to all:
			int lowestId = Collections.min(fd.getUnsucpected());
			System.out.println(">> Min ID is: " + lowestId);
			if (id == lowestId) {
				System.out.println(">> I'm the transaction sender. Sending transaction...");
				ZooHelper.createNewNode(zooKeeper, ZooHelper.TRANSACTION_ROOT, new byte[0], CreateMode.PERSISTENT);
				System.out.println(">> I'm the transaction sender. Transaction sent");
			}

			// wait for transaction
			System.out.println(">> Waiting for the transaction...");
			waitForTransaction(zooKeeper);
			System.out.println(">> Transaction received");

			// ***START Modified 3PC***
			System.out.println(">> Starting Modified_3PC with: " + vote + "...");
			boolean m3pcResult = new Modified_3PC(zooKeeper, fd, out).start(n, id, crashTime, crashRound, vote, lowestId);
			System.out.println(">> Finished Modified_3PC with: " + m3pcResult);
		}

	}

	private static void waitForTransaction(ZooKeeper k) {
		String prefix = ">>> Waiting for the transaction... ";
		
		Semaphore sem = new Semaphore(0);
		try {
			System.out.println(prefix + "check if transaction was recieved");
			if (!ZooHelper.checkNodeExistenceAndAddWatcher(k, ZooHelper.TRANSACTION_ROOT, new Watcher() {
				@Override
				public void process(WatchedEvent __) {
					System.out.println(prefix + "transaction recieved. Release lock...");
					sem.release();
				}
			})) {
				System.out.println(prefix + "transaction was not recieved yet. Wait...");
				sem.acquire();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
