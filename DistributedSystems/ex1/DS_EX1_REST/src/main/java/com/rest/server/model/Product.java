package com.rest.server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.rest.server.resources.MainDbResource;

@XmlRootElement(name = "product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name = "id", required = false)
	public int id;
	
	@XmlElement(name = "name", required = true)
	public String name;
	
	@XmlElement(name = "category", required = true)
	public String category;
	
	@XmlElement(name = "description", required = true)
	public String description;
	
	@XmlElement(name = "links", required = false)
	public List<Link> links  = new ArrayList<>();
	
	private UriInfo baseUriInfo;
	
	private Map<Integer, Integer> storeAndPriceList = new HashMap<>();
	private Map<Integer, CustomerReview> customerReviews = new HashMap<>();
	private int newCustomerReviewID = 0;
	
	public Product() {}
	
	public Product(int id, String name, String category, String description) {
		this.id = id;
		this.setName(name);
		this.setCategory(category);
		this.setDescription(description);
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
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	///////////////////////////////////////////////////
	// more getters
	///////////////////////////////////////////////////
	/**
	 * @return the review or null if doesn't exist
	 */
	public CustomerReview getCustomerReviewByID(int reviewID) {
		return customerReviews.get(reviewID);
	}
	
	/**
	 * @return the price or null if store doesn't exist
	 */
	public Integer getPriceInStore(int storeID) {
		return storeAndPriceList.get(storeID);
	}
	
	public Collection<CustomerReview> getAllReviews() {
		return customerReviews.values();
	}
	
	public Map<Integer, Integer> getAllStoresAndPrices() {
		return storeAndPriceList;
	}
	
	/**
	 * @return the average rating or null if there are no reviews
	 */
	public Double getAverageRating() {
		int numOfReviews = customerReviews.size();
		if(numOfReviews == 0)
			return null;
		
		double sum = 0;
		for (CustomerReview customerReview : customerReviews.values()) {
			sum += customerReview.getRating();
		}
		
		return sum / numOfReviews;
	}
	
	///////////////////////////////////////////////////
	// delete
	///////////////////////////////////////////////////
	/**
	 * @return true if deleted, or false if store doesn't exist
	 */
	public boolean deleteStoreById(int storeID) {
		return storeAndPriceList.remove(storeID) != null;
	}
	
	/**
	 * @return true if deleted, or false if review doesn't exist
	 */
	public boolean deleteReviewById(int reviewID) {
		return customerReviews.remove(reviewID) != null;
	}
	
	///////////////////////////////////////////////////
	// add
	///////////////////////////////////////////////////
	public CustomerReview addNewCustomerReview(int rating, String review) {
		CustomerReview customerReview = new CustomerReview(++newCustomerReviewID, id, review, rating);
		customerReview.setLinks(makeBase());
		customerReviews.put(customerReview.getId(), customerReview);
		return customerReview;
	}
	
	/**
	 * @return true if new, or false if updated an existing entry
	 */
	public boolean addStoreWithPrice(int storeID, int price) {
		return storeAndPriceList.put(storeID, price) == null;
	}
	
	///////////////////////////////////////////////////
	// links
	///////////////////////////////////////////////////
	private UriBuilder makeBase() {
		UriBuilder base = baseUriInfo.getBaseUriBuilder()
				.path(MainDbResource.class)
				.path("products")
				.path(Integer.toString(this.getId()));
		return base;
	}
	public void setLinks(UriInfo uriInfo) {
		baseUriInfo = uriInfo;
		links.add(new Link().setLink(makeBase().build().toString(), "self"));
		
		String uri = makeBase().path("stores").build().toString();
		links.add(new Link().setLink(uri, "stores"));
		
		uri = makeBase().path("customerReviews").build().toString();
		links.add(new Link().setLink(uri, "customerReviews"));
		
		uri = makeBase().path("average_rating").build().toString();
		links.add(new Link().setLink(uri, "average rating"));
	}
	
	///////////////////////////////////////////////////
	// overrides
	///////////////////////////////////////////////////
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Product ? ((Product)obj).getId() == this.getId() : false;
	}
	
	@Override
	public String toString() {
		return "product " + id + " " + name + " " + category + " " + description;
	}
}
