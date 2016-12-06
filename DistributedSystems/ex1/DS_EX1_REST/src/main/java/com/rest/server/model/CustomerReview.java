package com.rest.server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "customerReview")
public class CustomerReview implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name = "id", required = false)
	private int id;
	
	@XmlElement(name = "productID", required = false)
	private int productID;
	
	@XmlElement(name = "review", required = true)
	private String review;
	
	@XmlElement(name = "rating", required = true)
	private int rating;
	
	@XmlElement(name = "links", required = false)
	public List<Link> links  = new ArrayList<>();
	
	public CustomerReview() {}
	
	public CustomerReview(int id, int productID, String review, int rating) {
		this.id = id;
		this.productID = productID;
		this.review = review;
		this.rating = rating;
	}
	
	///////////////////////////////////////////////////
	// getters and setters
	///////////////////////////////////////////////////
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}

	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	///////////////////////////////////////////////////
	// links
	///////////////////////////////////////////////////
	public void setLinks(UriBuilder productBaseUri) {		
		links.add(new Link().setLink(productBaseUri
				.path("customerReviews")
				.path(Integer.toString(this.getId()))
				.build().toString(), "self"));
	}
	
	///////////////////////////////////////////////////
	// Overrides
	///////////////////////////////////////////////////
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Product ? ((CustomerReview)obj).id == this.id : false;
	}
	
	@Override
	public String toString() {
		return "customerReviews " + productID + " " + id + " " + rating + " " + review;
	}
}