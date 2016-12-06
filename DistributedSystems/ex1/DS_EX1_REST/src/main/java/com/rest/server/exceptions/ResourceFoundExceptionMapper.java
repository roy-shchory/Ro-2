package com.rest.server.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ResourceFoundExceptionMapper implements ExceptionMapper<ResourceNotFoundException> {

	@Override
	public Response toResponse(ResourceNotFoundException ex) {
		return Response.status(Status.NOT_FOUND)
				.entity(new ErrorMessage(ex.getMessage(), 404))
				.build();
	}

}

