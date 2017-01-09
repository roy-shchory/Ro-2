package com.zook.zook;

import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;

public class ZooHelper {
	public static final String EX_ROOT = "/ds";

	public static final String BARRIER_ROOT = EX_ROOT + "/zk_barrier";
	public static final String FAILURE_DETECTOR_ROOT = EX_ROOT + "/failure_detector";
	public static final String TRANSACTION_ROOT = EX_ROOT + "/transaction";

	public static final String MODIFIED_3PC_ROOT = EX_ROOT + "/modified3pc";
	public static final String VOTES_3PC_ROOT = MODIFIED_3PC_ROOT + "/votes";
	public static final String RESULT_3PC_ROOT = MODIFIED_3PC_ROOT + "/result";

	public static final String CONSENSUS_ROOT = EX_ROOT + "/consensus";
	public static final String ROUNDS_CONSENSUS_ROOT = CONSENSUS_ROOT + "/rounds";
	public static final String RESULT_CONSENSUS_ROOT = CONSENSUS_ROOT + "/result";

	public static boolean createNewNode(ZooKeeper k, String path, byte[] data, CreateMode m) {
		try {
			k.create(path, data, Ids.OPEN_ACL_UNSAFE, m);
			return true;
		} catch (KeeperException e) {
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean isNodeExist(ZooKeeper k, String path) {
		return checkNodeExistenceAndAddWatcher(k, path, null);
	}

	public static boolean checkNodeExistenceAndAddWatcher(ZooKeeper k, String path, Watcher w) {
		try {
			return k.exists(path, w) != null;
		} catch (KeeperException | InterruptedException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static List<String> addChildrenWatcher(ZooKeeper k, String parentPath, Watcher w) {
		try {
			return k.getChildren(parentPath, w);
		} catch (KeeperException | InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] getDataFromNodeIfExists(ZooKeeper k, String path) {
		try {
			if (isNodeExist(k, path))
				return k.getData(path, false, null);
		} catch (KeeperException | InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static byte[] bool2byteArray(boolean b) {
		byte[] a = new byte[1];
		a[0] = (byte) (b ? 1 : 0);
		return a;
	}
	
	public static boolean byteArray2Bool(byte[] a) {
		return a != null && a[0] == 1;
	}
}
