package DatabaseObjects;

import java.io.Serializable;

public class CustomerReview implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int productID;
	private String review;
	private int rating;
	
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