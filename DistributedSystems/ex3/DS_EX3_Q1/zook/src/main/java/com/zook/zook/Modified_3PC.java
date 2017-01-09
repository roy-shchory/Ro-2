package com.zook.zook;

import java.io.BufferedWriter;
import java.io.IOException;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class Modified_3PC {

	private ZooKeeper zooKeeper;
	private FailureDetector failureDetector;
	private BufferedWriter out;

	public Modified_3PC(ZooKeeper zooKeeper, FailureDetector failureDetector, BufferedWriter out) {
		this.zooKeeper = zooKeeper;
		this.failureDetector = failureDetector;
		this.out = out;

		ZooHelper.createNewNode(zooKeeper, ZooHelper.MODIFIED_3PC_ROOT, new byte[0], CreateMode.PERSISTENT);
	}

	public boolean start(int n, int id, String crashTime, int crashRound, boolean vote, int coordinatorId)
			throws CrashedException, IOException, InterruptedException, KeeperException {
		// ***START 3PC_MODIFIED***
		// if 0-V, crash!
		System.out.println(">>> Checking if I need to crash... for (0-V) only...");
		if (crashRound == 0 && "V".equals(crashTime)) {
			System.out.println(">>> Crashing...");
			CrashedException.crash(out);
		}

		// send vote to coordinator (the transaction's sender):
		byte[] voteData = ZooHelper.bool2byteArray(vote);
		System.out.println(">>> Sending my vote (" + voteData[0] + ") to coordinator (" + coordinatorId + ")...");
		ZooHelper.createNewNode(zooKeeper, ZooHelper.VOTES_3PC_ROOT, new byte[0], CreateMode.PERSISTENT);
		ZooHelper.createNewNode(zooKeeper, ZooHelper.VOTES_3PC_ROOT + "/" + id, voteData, CreateMode.PERSISTENT);
		System.out.println(">>> Vote sent to coordinator");

		// only coordinator:
		if (id == coordinatorId) {
			String cPrefix = ">>>> I'm the coordinator - ";

			// only coordinator - wait for votes or until new suspicion, and
			// send results to all:
			System.out.println(cPrefix + "waiting for votes...");
			byte[] voteResultData = ZooHelper.bool2byteArray(waitForVotes());
			System.out.println(cPrefix + "got vote result: " + voteResultData[0]);
			System.out.println(cPrefix + "sending vote result to all...");
			ZooHelper.createNewNode(zooKeeper, ZooHelper.RESULT_3PC_ROOT, voteResultData, CreateMode.PERSISTENT);
			System.out.println(cPrefix + "vote result sent to all...");
		}

		// wait for coordinator results, or until the coordinator's suspicion:
		System.out.println(">>> Wait for vote result from coordinator...");
		boolean consensusInitValue = waitForCoordinatorsResult(coordinatorId);
		System.out.println(">>> Vote result received from coordinator: " + consensusInitValue);

		// ***START CONSENSUS (M&R)***
		System.out.println(">>> Starting consensus with: " + consensusInitValue + "...");
		boolean consensusResult = new Consensus(zooKeeper, failureDetector, out)
				.start(n, id, crashTime, crashRound, consensusInitValue);
		System.out.println(">>> Finished consensus with: " + consensusResult);

		return consensusResult;
	}

	// [start] coordinator code
	private boolean waitForVotes() throws InterruptedException {
		final AtomicBooleanLock sml = new AtomicBooleanLock();

		failureDetector.addOnFailureListener(index -> {
			sml.setAndRelese(false);
		});

		ZooHelper.addChildrenWatcher(zooKeeper, ZooHelper.VOTES_3PC_ROOT, new Watcher() {
			@Override
			public void process(WatchedEvent __) {
				getVotesAndReleseLock(sml);
			}
		});

		getVotesAndReleseLock(sml);

		return sml.waitForResult();
	}

	private void getVotesAndReleseLock(AtomicBooleanLock sml) {
		Boolean result = getVotesFromUnsucpectedNodes();
		if (result != null)
			sml.setAndRelese(result);
	}

	/**
	 * @return null if not all voted yet, or the vote result (AND on all of the
	 *         votes)
	 */
	private Boolean getVotesFromUnsucpectedNodes() {
		boolean temp = true;
		for (int nodeIndex : failureDetector.getUnsucpected()) {
			String path = ZooHelper.VOTES_3PC_ROOT + "/" + nodeIndex;
			byte[] data = ZooHelper.getDataFromNodeIfExists(zooKeeper, path);
			if (data == null)
				return null;
			temp &= ZooHelper.byteArray2Bool(data);
		}

		return temp;
	}
	// [end]

	// [start] wait for coordinator
	private boolean waitForCoordinatorsResult(int coordinatorId) throws InterruptedException {
		final AtomicBooleanLock sml = new AtomicBooleanLock();

		// if coordinator will fail before sending the result, we will return
		// FALSE:
		failureDetector.addOnFailureListener(index -> {
			if (index == coordinatorId) {
				sml.setAndRelese(false);
			}
		});

		// if coordinator's result exists, then return result.
		//
		// if not, we place a watch for it.
		//
		// also, we check if he is still alive (could have failed before sending
		// the result, and before the 'addOnFailureListener' function call) - if
		// not return FALSE
		if (ZooHelper.checkNodeExistenceAndAddWatcher(zooKeeper, ZooHelper.RESULT_3PC_ROOT, new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				sml.setAndRelese(getResultValueFromCoordinator());
			}
		})) {
			sml.setAndRelese(getResultValueFromCoordinator());
		} else if (!failureDetector.isAlive(coordinatorId)) {
			sml.setAndRelese(false);
		}

		// wait for result or coordinator's fail
		return sml.waitForResult();
	}

	private boolean getResultValueFromCoordinator() {
		return ZooHelper.byteArray2Bool(ZooHelper.getDataFromNodeIfExists(zooKeeper, ZooHelper.RESULT_3PC_ROOT));
	}

	// [end]
}
