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

		try (BufferedWriter out = new BufferedWriter(new FileWriter(new File("nbac_output_" + id + ".txt")))) {
			// init zookeeper:
			ZooKeeper zooKeeper = new ZkConnector().connect().getZooKeeper();

			// init failure detector:
			FailureDetector fd = new FailureDetector(zooKeeper, n, id);

			// enter barrier:
			new Barrier(zooKeeper, n, id).enter();

			// lowest id (that didn't crash) sends the transaction to all:
			int lowestId = Collections.min(fd.getUnsucpected());
			if (id == lowestId)
				ZooHelper.createNewNode(zooKeeper, ZooHelper.TRANSACTION_ROOT, new byte[0], CreateMode.PERSISTENT);

			// wait for transaction
			waitForTransaction(zooKeeper);

			// ***START Modified 3PC***
			new Modified_3PC(zooKeeper, fd, n, id, vote, crashTime, crashRound, out).start(lowestId);
		}

	}

	private static void waitForTransaction(ZooKeeper k) {
		Semaphore sem = new Semaphore(0);
		try {
			if (!ZooHelper.checkNodeExistenceAndAddWatcher(k, ZooHelper.TRANSACTION_ROOT, new Watcher() {
				@Override
				public void process(WatchedEvent __) {
					sem.release();
				}
			}))
				sem.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
