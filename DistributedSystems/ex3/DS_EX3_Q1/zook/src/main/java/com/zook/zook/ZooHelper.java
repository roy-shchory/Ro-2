package com.zook.zook;

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
//		try {
//			return k.exists(path, false) != null;
//		} catch (KeeperException | InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//		return false;
	}
	
	public static boolean checkNodeExistenceAndAddWatcher(ZooKeeper k, String path, Watcher w) {
		try {
			return k.exists(path, w) != null;
		} catch (KeeperException | InterruptedException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
