package com.rest.server.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/messages")
public class Message {
	static int counter = 10;
	
	public Message() {
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public int getInts() throws Exception {
		
		List<Integer> a = new ArrayList<>();
		a.add(1);
		a.add(57);
		//throw new Exception("HI");
		return ++counter;
	}
	
}
