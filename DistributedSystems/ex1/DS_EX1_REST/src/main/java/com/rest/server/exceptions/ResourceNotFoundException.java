package com.rest.server.exceptions;

public class ResourceNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(String objectType, int wrongId){
		super("Invalid " + objectType + " ID (" + wrongId + ")");
	}
	
	public ResourceNotFoundException() {
		super();
	}

}
