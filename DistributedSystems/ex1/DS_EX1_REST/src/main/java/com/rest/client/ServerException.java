package com.rest.client;

import com.rest.server.exceptions.ErrorMessage;

public class ServerException extends Exception {
	private static final long serialVersionUID = 1L;
	
	ErrorMessage e;

	public ServerException(ErrorMessage e) {
		this.e = e;
	}
	
	@Override
	public String toString() {
		return "## Error from server (" + e.getErrorCode() + "): " + e.getErrorMessage();
	}

}
