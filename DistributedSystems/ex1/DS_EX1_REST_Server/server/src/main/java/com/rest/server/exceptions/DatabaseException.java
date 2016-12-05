package com.rest.server.exceptions;

public class DatabaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DatabaseException(String arg) {
		super(arg);
	}
	
	public static DatabaseException generateInvalidIdError(String objectType, int wrongId) {
		return new DatabaseException("Invalid " + objectType + " ID (" + wrongId + ")");
	}
}
