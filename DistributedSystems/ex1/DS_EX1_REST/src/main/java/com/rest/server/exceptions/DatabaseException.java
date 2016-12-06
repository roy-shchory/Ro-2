package com.rest.server.exceptions;

public class DatabaseException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private String errorMessage;
	
	public String getErrorMessage(){
		return this.errorMessage;
	}
	
	public DatabaseException() {
		super();
	}
	
	public DatabaseException(String error){
		super(error);
		this.errorMessage=error;
	}
	
}
