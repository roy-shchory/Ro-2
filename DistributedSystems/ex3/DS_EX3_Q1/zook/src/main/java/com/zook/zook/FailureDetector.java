package com.zook.zook;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class FailureDetector {
	private static final String PATH = ZooHelper.FAILURE_DETECTOR_ROOT;

	private ZooKeeper zooKeeper;
	private int id;
	private int size;

	public FailureDetector(ZooKeeper zooKeeper, int size, int id) {
		this.zooKeeper = zooKeeper;
		this.id = id;
		this.size = size;

		ZooHelper.createNewNode(zooKeeper, PATH, new byte[0], CreateMode.PERSISTENT);
		ZooHelper.createNewNode(zooKeeper, getPath(), new byte[0], CreateMode.EPHEMERAL);
	}

	public void addOnFailureListener(Consumer<Integer> listener) {
		for (int i = 1; i <= this.size; ++i) {
			final int nodeIndex = i;
			ZooHelper.checkNodeExistenceAndAddWatcher(zooKeeper, getPath(i), new Watcher() {
				@Override
				public void process(WatchedEvent e) {
					if (e.getType() == Watcher.Event.EventType.NodeDeleted)
						listener.accept(nodeIndex);
				}
			});
		}
	}

	// [start] get path
	private String getPath() {
		return PATH + "/" + id;
	}

	private String getPath(int i) {
		return PATH + "/" + i;
	}
	// [end]

	// [start] liveness
	private boolean[] getLivenessArray() {
		boolean[] liveness = new boolean[size];

		for (int i = 0; i < liveness.length; ++i)
			liveness[i] = ZooHelper.isNodeExist(zooKeeper, getPath(i + 1));

		return liveness;
	}

	private List<Integer> getLivenessAux(boolean selectedLivenessValue) {
		boolean[] liveness = getLivenessArray();

		List<Integer> result = new ArrayList<>();

		for (int i = 0; i < liveness.length; ++i)
			if (liveness[i] == selectedLivenessValue)
				result.add(i + 1);

		return result;
	}

	public List<Integer> getUnsucpected() {
		return getLivenessAux(true);
	}

	public List<Integer> getSucpected() {
		return getLivenessAux(false);
	}
	// [end]
}
