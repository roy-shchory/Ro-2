package com.rest.server.exceptions;

public class ResourceNotFoundException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(String error){
		super(error);
	}
	
	public ResourceNotFoundException() {
		super();
	}
	
	public static ResourceNotFoundException generateInvalidIdError(String objectType, int wrongId) {
		return new ResourceNotFoundException("Invalid " + objectType + " ID (" + wrongId + ")");
	}

}
