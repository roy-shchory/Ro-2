package com.zook.zook;

import java.io.BufferedWriter;
import java.io.IOException;

public class CrashedException extends Exception {

	private static final long serialVersionUID = 1307559997337264041L;
	
	public static void crash(BufferedWriter out) throws CrashedException, IOException {
		out.write("crashed");
		out.newLine();
		out.flush();
		throw new CrashedException();
	}
}
