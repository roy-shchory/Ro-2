package com.rest.server.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DatabaseExceptionMapper implements ExceptionMapper<DatabaseException> {

	@Override
	public Response toResponse(DatabaseException ex) {
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(new ErrorMessage(ex.getMessage(), 500))
				.build();
	}

}

