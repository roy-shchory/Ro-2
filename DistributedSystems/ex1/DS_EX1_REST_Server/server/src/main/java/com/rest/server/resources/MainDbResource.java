package com.rest.server.resources;

import com.rest.server.model.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.rest.server.exceptions.*;

@Path("/app")
@Consumes({MediaType.APPLICATION_JSON ,MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML})
public class MainDbResource {

	static MainDB mainDB = new MainDB();
	
	@GET
	@Path("/users")
	public User a(){
		return new User("ron",25);
	}
	
	///////////////////////////////////////////////////
	// Add
	///////////////////////////////////////////////////
	// 1
	@POST
	@Path("/users")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addNewUser(User newUser, @Context UriInfo uriInfo) {
		User user = mainDB.addNewUser(newUser.getUser_name());
		
		String newId = String.valueOf(user.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.
				created(uri)
				.entity(user)
				.build();
	}

	// 1
	@Path("/stores")
	public Response addNewStore(Store newStore, @Context UriInfo uriInfo) {
		Store store = mainDB.addNewStore(newStore.getName(), newStore.getPhone_number());
		
		String newId = String.valueOf(store.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.
				created(uri)
				.entity(store)
				.build();
	}
	
	// 1
	@Path("/products")
	public Product addNewProduct(String name, String category, String description) {
	}
	
	// 1
	@Path("/products/{productID}/customerReviews")
	public CustomerReview addNewCustomerReview(@PathParam("productID") int productID, int rating, String review) throws DatabaseException  {
	}
	
	///////////////////////////////////////////////////
	// GetByID
	///////////////////////////////////////////////////
	// 2
	@GET
	@Path("/users/{userID}")
	public User getUserByID(@PathParam("userID") int id) throws ResourceNotFoundException {
		return mainDB.getUserByID(id);
	}
	/*
	// 2
	public Store getStoreByID(int id) throws DatabaseException {
	}
	
	// 2
	public Product getProductByID(int id) throws DatabaseException {
	}
	
	// 2
	public CustomerReview getCustomerReviewByID(int productID, int reviewID) throws DatabaseException {
	}
	
	///////////////////////////////////////////////////
	// Update
	///////////////////////////////////////////////////
	// 3
	public User updateUser(int id, String name) throws DatabaseException {
	}
	
	// 3
	public Store updateStore(int id, String name, String phoneNumber) throws DatabaseException {
	}
	
	// 3
	public Product updateProduct(int id, String name, String category, String description) throws DatabaseException {
	}
	
	// 3
	public CustomerReview updateCustomerReview(int productID, int reviewID, int rating, String review) throws DatabaseException  {
	}	
	
	///////////////////////////////////////////////////
	// Delete
	///////////////////////////////////////////////////
	// 4
	public void deleteProduct(int productID) throws DatabaseException {
	}
	
	// 5
	public void deleteStore(int storeID) throws DatabaseException {
	}
	
	// 6
	public void deleteUser(int userID) throws DatabaseException {
	}
	
	// 7
	public void deleteCustomerReview(int productID, int reviewID) throws DatabaseException {
	}
	
	///////////////////////////////////////////////////
	// GetAll
	///////////////////////////////////////////////////
	// 8
	public Collection<Product> getAllProductsByCategory(String category) {
	}
	
	// 8
	public Collection<Product> getAllProductsByMaxPrice(int maxPrice) {
	}
	
	// 9
	public Collection<Store> getAllStores() {
	}
	
	// 10
	public Collection<CustomerReview> getAllCustomerReviews(int productID) throws DatabaseException {
	}
	
	// 11
	public Collection<User> getAllUsers() {
	}
	
	// 12
	public Collection<Pair<Integer, Integer>> getAllStoresAndPricesBySpecificProduct(int productID) throws DatabaseException {
	}
	
	// 13
	public Collection<Pair<Integer, Integer>> getAllProductsAndPricesInStore(int storeID) throws DatabaseException {
	}
	
	///////////////////////////////////////////////////
	// More getters
	///////////////////////////////////////////////////
	// 14
	public double getAverageRatingOfProduct(int productID) throws DatabaseException {
	}
	
	///////////////////////////////////////////////////
	// Link store and product
	///////////////////////////////////////////////////
	// 15
	public void linkStoreAndProduct(int storeID, int productID, int priceInStore) throws DatabaseException {
	}
	
	private int checkIfProductIsLinkedToStore(int productID, int storeID) throws DatabaseException {
	}
	
	///////////////////////////////////////////////////
	// User's cart
	///////////////////////////////////////////////////
	// 16
	public void addToCart(int userID, int productID, int storeID) throws DatabaseException {
	}
	
	// 17
	public void deleteFromCart(int userID, int productID, int storeID) throws DatabaseException {
	}
	
	// 18
	public int payForUserCart(int userID) throws DatabaseException {
	}
	
	// 19
	public Collection<Pair<Integer, Integer>> getProductIDsAndStoreIDsFromCart(int userID) throws DatabaseException {
	}
	
	// 20
	public Collection<Pair<Integer, Integer>> getProductIDsAndStoreIDsBought(int userID) throws DatabaseException {
	}
	
	// 21
	public Collection<Integer> getAllUserIDsThatBoughtTheProduct(int productID) throws DatabaseException {
	}
	*/
}

