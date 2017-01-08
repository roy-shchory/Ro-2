package com.zook.zook;

import java.io.BufferedWriter;
import java.io.IOException;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
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

	public Modified_3PC(ZooKeeper zooKeeper, FailureDetector failureDetector, int n, int id, boolean vote,
			String crashTime, int crashRound, BufferedWriter out) {
		this.zooKeeper = zooKeeper;
		this.failureDetector = failureDetector;

		this.n = n;
		this.id = id;
		this.vote = vote;
		this.crashTime = crashTime;
		this.crashRound = crashRound;

		this.out = out;

		ZooHelper.createNewNode(zooKeeper, ZooHelper.MODIFIED_3PC_ROOT, new byte[0], CreateMode.PERSISTENT);
	}

	public void start(int coordinatorId) throws CrashedException, IOException, InterruptedException {
		// ***START 3PC_MODIFIED***
		// if 0-V, crash!
		if (crashRound == 0 && "V".equals(crashTime))
			CrashedException.crash(out);

		// send vote to coordinator (the transaction's sender):
		byte[] voteData = new byte[1];
		voteData[0] = (byte) (vote ? 1 : 0);
		ZooHelper.createNewNode(zooKeeper, ZooHelper.VOTES_3PC_ROOT + "/" + id, voteData, CreateMode.PERSISTENT);

		// only coordinator:
		if (id == coordinatorId) {
			// only coordinator - wait for votes or until new suspicion, and
			// send results to all:
			byte[] voteResultData = ZooHelper.bool2byteArray(waitForVotes());
			ZooHelper.createNewNode(zooKeeper, ZooHelper.RESULT_3PC_ROOT, voteResultData, CreateMode.PERSISTENT);
		}

		// wait for coordinator results, or until the coordinator's suspicion:
		boolean consensusInitValue = waitForCoordinatorsResult(coordinatorId);

		// ***START CONSENSUS (M&R)***
		new Consensus(zooKeeper, failureDetector, n, id, crashTime, crashRound, out).start(consensusInitValue);
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
