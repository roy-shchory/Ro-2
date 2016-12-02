package DatabaseObjects;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String category;
	private String description;
	private Map<Integer, Integer> storeAndPriceList;
	private Map<Integer, CustomerReview> customerReviews;
	private int newCustomerReviewID;
	
	public Product(int id, String name, String category, String description) {
		this.id = id;
		this.setName(name);
		this.setCategory(category);
		this.setDescription(description);
		this.storeAndPriceList = new HashMap<>();
		this.customerReviews = new HashMap<>();
		this.newCustomerReviewID = 0;
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
