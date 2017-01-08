package com.zook.zook;

public class AtomicBooleanLock {
	Boolean innerBool;

	public AtomicBooleanLock() {
		innerBool = null;
	}

	// public synchronized Boolean getInnerBool() {
	// return innerBool;
	// }
	//
	// public synchronized void setInnerBool(Boolean innerBool) {
	// this.innerBool = innerBool;
	// }

	public synchronized boolean waitForResult() throws InterruptedException {
		while (innerBool == null)
			wait();
		return innerBool;
	}

	public synchronized void setAndRelese(boolean releseValue) {
		if (innerBool != null)
			return;
		innerBool = releseValue;
		notifyAll();
	}
}
