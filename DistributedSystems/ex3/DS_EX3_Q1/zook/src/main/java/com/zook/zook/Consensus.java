package com.zook.zook;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class Consensus {
	private ZooKeeper zooKeeper;
	private FailureDetector failureDetector;
	private BufferedWriter out;

	private int crashRound;
	private String crashTime;

	public Consensus(ZooKeeper zooKeeper, FailureDetector failureDetector, BufferedWriter out) {
		this.zooKeeper = zooKeeper;
		this.failureDetector = failureDetector;
		this.out = out;

		ZooHelper.createNewNode(zooKeeper, ZooHelper.CONSENSUS_ROOT, new byte[0], CreateMode.PERSISTENT);
		ZooHelper.createNewNode(zooKeeper, ZooHelper.ROUNDS_CONSENSUS_ROOT, new byte[0], CreateMode.PERSISTENT);
	}

	public boolean start(int n, int id, String crashTime, int crashRound, boolean startValue)
			throws CrashedException, IOException, InterruptedException, KeeperException {
		this.crashTime = crashTime;
		this.crashRound = crashRound;

		AtomicBooleanLock decidedValue = new AtomicBooleanLock();

		// init the "decided" listener - if a decision will be made,
		// decidedValue will store the result
		initOnDecidedListener(decidedValue);

		// init consensus params:
		int round = 0;
		ConsensusValue myEstimation = ConsensusValue.createConsensusValue(startValue);

		// start while not decided loop:
		while (decidedValue.isSet()) {
			// init round params:
			int coordinatorId = (round % n) + 1;
			ConsensusValue estimationFromCoordinator = ConsensusValue.NIL;
			round++;
			
			// init the round's node, and other paths
			final String ROUND_PATH = ZooHelper.ROUNDS_CONSENSUS_ROOT + "/" + round;
			ZooHelper.createNewNode(zooKeeper, ROUND_PATH, new byte[0], CreateMode.PERSISTENT);
			
			final String ROUND_VOTES_PATH = ROUND_PATH + "/votes";
			ZooHelper.createNewNode(zooKeeper, ROUND_VOTES_PATH, new byte[0], CreateMode.PERSISTENT);
			
			final String ROUND_MY_VOTE_PATH = ROUND_VOTES_PATH + "/" + id;
			final String ROUND_COORDINATOR_VOTE_PATH = ROUND_VOTES_PATH + "/" + coordinatorId;

			// check if need to crash
			crashMe(round, "A");

			// steps 3-6:
			if (id == coordinatorId) {
				estimationFromCoordinator = myEstimation;
			} else {
				if (waitForCoordinatorsResult(ROUND_COORDINATOR_VOTE_PATH, coordinatorId))
					estimationFromCoordinator = getVoteFromNode(ROUND_COORDINATOR_VOTE_PATH);
			}

			// check if need to crash
			crashMe(round, "B");

			// step 7 - send to all
			ZooHelper.createNewNode(zooKeeper, ROUND_MY_VOTE_PATH , estimationFromCoordinator.getAsByteArray(), CreateMode.PERSISTENT);

			// check if need to crash
			crashMe(round, "C");
			
			// step 8, 9 - wait for a majority
			Set<ConsensusValue> votesFromQuorum = waitForQuorumVote(ROUND_VOTES_PATH, n);
			
			// step 10:
			ConsensusValue step10 = getValueIfAllValuesAreTheSameAndNotNil(votesFromQuorum);
			if (step10 != null) {
				decidedValue.setAndRelese(step10.getAsBoolean());
				// send result to all:
				ZooHelper.createNewNode(zooKeeper, ZooHelper.RESULT_CONSENSUS_ROOT, step10.getAsByteArray(), CreateMode.PERSISTENT);
				
				myEstimation = step10;
			}
			
			// step 11:
			ConsensusValue step11 = getValueIfAllValuesAreTheSameAndNilExists(votesFromQuorum);
			if (step11 != null) {
				myEstimation = step11;
			}
			
			printOutEstimation(myEstimation);
		}

		// return the consensus decision:
		boolean consensusResult = decidedValue.getInnerBool(); // can't be null because we exited
															   // the while loop
		printOutEstimation(ConsensusValue.createConsensusValue(consensusResult));
		return consensusResult;
	}

	// [start] get result if decided
	private void initOnDecidedListener(AtomicBooleanLock a) {
		if (ZooHelper.checkNodeExistenceAndAddWatcher(zooKeeper, ZooHelper.RESULT_CONSENSUS_ROOT, new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				a.setAndRelese(getDecidedResultData());
			}
		}))
			a.setAndRelese(getDecidedResultData());
	}

	private boolean getDecidedResultData() {
		return ZooHelper.byteArray2Bool(ZooHelper.getDataFromNodeIfExists(zooKeeper, ZooHelper.RESULT_CONSENSUS_ROOT));
	}
	// [end]

	// [start] wait for coordinators vote
	/**
	 * waits for coordinator's result, or until it's failure
	 * @param VOTES_PATH
	 * @param coordinatorId
	 * @return true if received, or false if failed
	 * @throws InterruptedException
	 */
	private boolean waitForCoordinatorsResult(final String path, int coordinatorId) throws InterruptedException {
		final AtomicBooleanLock sml = new AtomicBooleanLock();

		// if coordinator will fail before sending the result, we will return
		// FALSE:
		failureDetector.addOnFailureListener(index -> {
			if (index == coordinatorId)
				sml.setAndRelese(false);
		});

		// if coordinator's result exists, then return TRUE.
		//
		// if not, we place a watch for it.
		//
		// also, we check if he is still alive (could have failed before sending
		// the result, and before the 'addOnFailureListener' function call) - if
		// not return FALSE
		if (ZooHelper.checkNodeExistenceAndAddWatcher(zooKeeper, path, new Watcher() {
			@Override
			public void process(WatchedEvent __) {
				sml.setAndRelese(true);
			}
		}))
			sml.setAndRelese(true);
		else if (!failureDetector.isAlive(coordinatorId))
			sml.setAndRelese(false);

		// wait for result or coordinator's fail
		return sml.waitForResult();
	}
	// [end]
	
	private ConsensusValue getVoteFromNode(String NODE_PATH) {
		return ConsensusValue.createConsensusValue(ZooHelper.getDataFromNodeIfExists(zooKeeper, NODE_PATH));
	}
	
	// [start] wait for quorum
	private Set<ConsensusValue> waitForQuorumVote(String VOTES_PATH, int n) throws KeeperException, InterruptedException {
		final AtomicBooleanLock sml = new AtomicBooleanLock();
		
		Watcher childrenWatcher = new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				try {
					if(zooKeeper.getChildren(VOTES_PATH, false).size() > n/2)
						sml.setAndRelese(true);
				} catch (KeeperException | InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		zooKeeper.getChildren(VOTES_PATH, childrenWatcher);
		childrenWatcher.process(null);
		
		sml.waitForResult();
		
		return getVotesSet(VOTES_PATH);		
	}
	
	private Set<ConsensusValue> getVotesSet(String VOTES_PATH) throws KeeperException, InterruptedException {
		Set<ConsensusValue> votes = new HashSet<>();
		
		for (String node : zooKeeper.getChildren(VOTES_PATH, false))
			votes.add(getVoteFromNode(VOTES_PATH + "/" + node));
		
		return votes;
	}
	// [end]
	
	// [start] analyze quorum result
	private ConsensusValue getValueIfAllValuesAreTheSameAndNotNil(Set<ConsensusValue> votesFromQuorum) {
		return votesFromQuorum.size() != 1 || votesFromQuorum.contains(ConsensusValue.NIL) ? null
				: votesFromQuorum.contains(ConsensusValue.TRUE) ? ConsensusValue.TRUE : ConsensusValue.FALSE;
	}
	
	private ConsensusValue getValueIfAllValuesAreTheSameAndNilExists(Set<ConsensusValue> votesFromQuorum) {
		return votesFromQuorum.size() != 2 || !votesFromQuorum.contains(ConsensusValue.NIL) ? null
				: votesFromQuorum.contains(ConsensusValue.TRUE) ? ConsensusValue.TRUE : ConsensusValue.FALSE;
	}
	// [end]
	
	// [start] crash handlers
	private void crashMe(int round, final String currentCrashTime) throws CrashedException, IOException {
		System.out.println(">> Checking if I need to crash... for (R-" + currentCrashTime + ") only...");
		if (crashRound != round || !currentCrashTime.equals(crashTime))
			return;
		System.out.println(">> Crashing...");
		CrashedException.crash(out);
	}
	// [end]

	private void printOutEstimation(ConsensusValue myEstimation) throws IOException {
		out.write(myEstimation == ConsensusValue.TRUE ? "yes" : "no");
		out.newLine();
		out.flush();
	}
}

enum ConsensusValue {
	TRUE(true), FALSE(false), NIL(null);
	private Boolean asBoolean;

	private ConsensusValue(Boolean value) {
		asBoolean = value;
	}

	public Boolean getAsBoolean() {
		return asBoolean;
	}

	public byte[] getAsByteArray() {
		byte[] a = new byte[1];
		a[0] = asBoolean == null ? 2 : (byte) (asBoolean ? 1 : 0);
		return a;
	}

	public static ConsensusValue createConsensusValue(Boolean b) {
		return b == null ? NIL : (b ? TRUE : FALSE);
	}
	
	public static ConsensusValue createConsensusValue(byte[] bs) {
		return bs[0] == 2 ? NIL : (bs[0] == 1 ? TRUE : FALSE);
	}
}
