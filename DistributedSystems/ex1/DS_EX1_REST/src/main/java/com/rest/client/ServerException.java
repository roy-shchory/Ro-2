package com.rest.client;

import com.rest.server.exceptions.ErrorMessage;

public class ServerException extends Exception {
	private static final long serialVersionUID = 1L;

	public ServerException(ErrorMessage e) {
		super("## Error from server (" + e.getErrorCode() + "): " + e.getErrorMessage());
	}
	
}
