package com.rest.server.exceptions;

public class DatabaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private static final long serialVersionUID = 1L;
	private String errorMessage;
	
	public String getErrorMessage(){
		return this.errorMessage;
	}
	
	public DatabaseException() {
		super();
	}
	
	public static DatabaseException generateInvalidIdError(String objectType, int wrongId) {
		super("Invalid " + objectType + " ID (" + wrongId + ")");
		this.errorMessage="Invalid " + objectType + " ID (" + wrongId + ")";
	}
}
