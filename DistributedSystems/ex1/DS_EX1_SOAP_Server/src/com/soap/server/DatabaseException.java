package com.soap.server;

public class DatabaseException extends Exception {
	private static final long serialVersionUID = 1L;

	public DatabaseException(String arg) {
		super(arg);
	}
	
	static DatabaseException generateInvalidIdError(String objectType, int wrongId) {
		return new DatabaseException("Invalid " + objectType + " ID (" + wrongId + ")");
	}
}
