package com.rest.client;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.rest.server.model.*;

public class ClientApp {
	public static void main(String[] args){
		Client client = ClientBuilder.newClient();
		
		WebTarget baseTarget = client.target("http://localhost:8080/SD_EX1_REST/webapi/app");
		WebTarget messagesTarget = baseTarget.path("users");
		WebTarget singleMessageTarget = messagesTarget.path("{messageId}");
		
		User newMessage = new User("Roy",5);
		Response postResponse = messagesTarget
			.request()
			.post(Entity.json(newMessage));
		if (postResponse.getStatus() != 201) {
			System.out.println("Error");
		}
		User createdUser = postResponse.readEntity(User.class);
		System.out.println(createdUser.getUser_name());
		
		User user1 = singleMessageTarget
				.resolveTemplate("messageId", "1")
				.request(MediaType.APPLICATION_JSON)
				.get(User.class);
		
		System.out.print(user1.getUser_name()+"\n");
	}
}
