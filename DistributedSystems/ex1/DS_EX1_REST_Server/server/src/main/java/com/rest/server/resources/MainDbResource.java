package com.rest.server.resources;

import com.rest.server.model.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
	
	///////////////////////////////////////////////////
	// Add
	///////////////////////////////////////////////////
	// 1
	@POST
	@Path("/users")
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
	@POST
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
	@POST
	@Path("/products")
	public Response addNewProduct(Product newProduct, @Context UriInfo uriInfo) {
		Product product = mainDB.addNewProduct(newProduct.getName(), newProduct.getCategory(), newProduct.getDescription());
		
		String newId = String.valueOf(product.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.
				created(uri)
				.entity(product)
				.build();
	}
	
	// 1
	@POST
	@Path("/products/{productID}/customerReviews")
	public Response addNewCustomerReview(@PathParam("productID") int productID, 
			CustomerReview newCustomerReview, @Context UriInfo uriInfo) throws ResourceNotFoundException  {
		CustomerReview customerReview = 
				mainDB.addNewCustomerReview(productID, newCustomerReview.getRating(), newCustomerReview.getReview());
		
		String newId = String.valueOf(customerReview.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.
				created(uri)
				.entity(customerReview)
				.build();
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
	
	// 2
	@GET
	@Path("/stores/{storeID}")
	public Store getStoreByID(@PathParam("storeID") int id) throws ResourceNotFoundException {
		return mainDB.getStoreByID(id);
	}
	
	// 2
	@GET
	@Path("/products/{productID}")
	public Product getProductByID(@PathParam("productID") int id) throws ResourceNotFoundException {
		return mainDB.getProductByID(id);
	}
	
	// 2
	@GET
	@Path("/products/{productID}/customerReviews/{reviewID}")
	public CustomerReview getCustomerReviewByID(
			@PathParam("productID") int productID, @PathParam("reviewID") int reviewID) throws ResourceNotFoundException {
		return mainDB.getCustomerReviewByID(productID, reviewID);
	}
	
	///////////////////////////////////////////////////
	// Update
	///////////////////////////////////////////////////
	// 3
	@PUT
	@Path("/users/{userID}")
	public User updateUser(@PathParam("userID") int id, User newUser) throws ResourceNotFoundException {
		return mainDB.updateUser(id, newUser.getUser_name());
	}
	
	// 3
	@PUT
	@Path("/stores/{storeID}")
	public Store updateStore(@PathParam("storeID") int id, Store newStore) throws ResourceNotFoundException {
		return mainDB.updateStore(id, newStore.getName(), newStore.getPhone_number());
	}
	
	// 3
	@PUT
	@Path("/products/{productID}")
	public Product updateProduct(@PathParam("productID") int id, Product newProduct) throws ResourceNotFoundException {
		return mainDB.updateProduct(id, newProduct.getName(), newProduct.getCategory(), newProduct.getDescription());
	}
	
	// 3
	@PUT
	@Path("/products/{productID}/customerReviews/{reviewID}")
	public CustomerReview updateCustomerReview(@PathParam("productID") int productID, 
			@PathParam("reviewID") int reviewID, CustomerReview newCustomerReview) throws ResourceNotFoundException  {
		return mainDB.updateCustomerReview(productID, reviewID, newCustomerReview.getRating(), newCustomerReview.getReview());
	}	
	
	///////////////////////////////////////////////////
	// Delete
	///////////////////////////////////////////////////
	// 4
	@DELETE
	@Path("/products/{productID}")
	public void deleteProduct(@PathParam("productID") int productID) throws ResourceNotFoundException {
		mainDB.deleteProduct(productID);
	}
	
	// 5
	@DELETE
	@Path("/stores/{storeID}")
	public void deleteStore(@PathParam("storeID") int storeID) throws ResourceNotFoundException {
		mainDB.deleteStore(storeID);
	}
	
	// 6
	@DELETE
	@Path("/users/{userID}")
	public void deleteUser(@PathParam("userID") int userID) throws ResourceNotFoundException {
		mainDB.deleteUser(userID);
	}
	
	// 7
	@DELETE
	@Path("/products/{productID}/customerReviews/{reviewID}")
	public void deleteCustomerReview(@PathParam("productID") int productID, @PathParam("reviewID") int reviewID) throws ResourceNotFoundException {
		mainDB.deleteCustomerReview(productID, reviewID);
	}
	
	///////////////////////////////////////////////////
	// GetAll
	///////////////////////////////////////////////////
	
	// 8
	@GET
	@Path("/products")
	public Collection<Product> getAllProductsByCategoryOrMaxPrice(
			@QueryParam("category") String category, 
			@QueryParam("maxPrice") Integer maxPrice) throws ResourceNotFoundException {
		if(category != null)
			return mainDB.getAllProductsByCategory(category);
		else if(maxPrice != null)
			return mainDB.getAllProductsByMaxPrice(maxPrice);
		throw new ResourceNotFoundException("must have a query param: category or maxPrice");
	}
	
	// 9
	@GET
	@Path("/stores")
	public Collection<Store> getAllStores() {
		return mainDB.getAllStores();
	}
	
	// 10
	@GET
	@Path("/products/{productID}/customerReviews}")
	public Collection<CustomerReview> getAllCustomerReviews(@PathParam("productID") int productID) throws ResourceNotFoundException {
		return mainDB.getAllCustomerReviews(productID);
	}
	
	// 11
	@GET
	@Path("/users")
	public Collection<User> getAllUsers() {
		return mainDB.getAllUsers();
	}
	/*
	// 12
	@GET
	@Path("/products/{productID}/stores")
	public Collection<Pair<Integer, Integer>> getAllStoresAndPricesBySpecificProduct(int productID) throws ResourceNotFoundException {
	}
	
	// 13
	public Collection<Pair<Integer, Integer>> getAllProductsAndPricesInStore(int storeID) throws ResourceNotFoundException {
	}
	/*
	///////////////////////////////////////////////////
	// More getters
	///////////////////////////////////////////////////
	// 14
	public double getAverageRatingOfProduct(int productID) throws ResourceNotFoundException {
	}
	
	///////////////////////////////////////////////////
	// Link store and product
	///////////////////////////////////////////////////
	// 15
	public void linkStoreAndProduct(int storeID, int productID, int priceInStore) throws ResourceNotFoundException {
	}
	
	private int checkIfProductIsLinkedToStore(int productID, int storeID) throws ResourceNotFoundException {
	}
	
	///////////////////////////////////////////////////
	// User's cart
	///////////////////////////////////////////////////
	// 16
	public void addToCart(int userID, int productID, int storeID) throws ResourceNotFoundException {
	}
	
	// 17
	public void deleteFromCart(int userID, int productID, int storeID) throws ResourceNotFoundException {
	}
	
	// 18
	public int payForUserCart(int userID) throws DatabaseException {
	}
	
	// 19
	public Collection<Pair<Integer, Integer>> getProductIDsAndStoreIDsFromCart(int userID) throws ResourceNotFoundException {
	}
	
	// 20
	public Collection<Pair<Integer, Integer>> getProductIDsAndStoreIDsBought(int userID) throws ResourceNotFoundException {
	}
	
	// 21
	public Collection<Integer> getAllUserIDsThatBoughtTheProduct(int productID) throws ResourceNotFoundException {
	}
	*/
}

