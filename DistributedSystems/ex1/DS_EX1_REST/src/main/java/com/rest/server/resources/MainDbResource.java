package com.rest.server.resources;

import com.rest.server.model.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import javax.ws.rs.core.Response.Status;

import com.rest.server.exceptions.*;

@Path("/app")
@Consumes({MediaType.APPLICATION_JSON ,MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML})
public class MainDbResource {

	static MainDB mainDB = new MainDB();
	
	@GET
	public Collection<Link> getMe(@Context UriInfo uriInfo) {
		Collection<Link> links  = new ArrayList<>();
		
		String uri = uriInfo.getAbsolutePathBuilder().path("users").build().toString();
		links.add(new Link().setLink(uri, "users"));
		
		uri = uriInfo.getAbsolutePathBuilder().path("stores").build().toString();
		links.add(new Link().setLink(uri, "stores"));
		
		uri = uriInfo.getAbsolutePathBuilder().path("products").build().toString();
		links.add(new Link().setLink(uri, "products"));
		
		return links;
	}
	
	///////////////////////////////////////////////////
	// Add
	///////////////////////////////////////////////////
	// 1
	@POST
	@Path("/users")
	public Response addNewUser(User newUser, @Context UriInfo uriInfo) {
		User user = mainDB.addNewUser(newUser.getUser_name(), uriInfo);
		
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
		Store store = mainDB.addNewStore(newStore.getName(), newStore.getPhone_number(), uriInfo);
		
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
		Product product = mainDB.addNewProduct(newProduct.getName(), newProduct.getCategory(), newProduct.getDescription(), uriInfo);
		
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
			@QueryParam("maxPrice") Integer maxPrice) {
		if(category != null)
			return mainDB.getAllProductsByCategory(category);
		else if(maxPrice != null)
			return mainDB.getAllProductsByMaxPrice(maxPrice);
		else
			return mainDB.getAllProducts();
	}
	
	// 9
	@GET
	@Path("/stores")
	public Collection<Store> getAllStores() {
		return mainDB.getAllStores();
	}
	
	// 10
	@GET
	@Path("/products/{productID}/customerReviews")
	public Collection<CustomerReview> getAllCustomerReviews(@PathParam("productID") int productID) throws ResourceNotFoundException {
		return mainDB.getAllCustomerReviews(productID);
	}
	
	// 11 + 21
	@GET
	@Path("/users")
	public Collection<User> getAllUsers(@QueryParam("productID") Integer productID) throws ResourceNotFoundException {
		if (productID != null)
			return mainDB.getAllUserIDsThatBoughtTheProduct(productID);
		else
			return mainDB.getAllUsers();
	}
	
	// 12
	@GET
	@Path("/products/{productID}/stores")
	public Collection<StorePricePair> getAllStoresAndPricesBySpecificProduct(@PathParam("productID") int productID) throws ResourceNotFoundException {
		Collection<StorePricePair> c = new ArrayList<>();
		
		for (Pair<Integer, Integer> p : mainDB.getAllStoresAndPricesBySpecificProduct(productID)) {
			c.add(new StorePricePair(p));
		}
		return c;
	}
	
	// 13
	@GET
	@Path("/stores/{storeID}/products")
	public Collection<ProductPricePair> getAllProductsAndPricesInStore(@PathParam("storeID") int storeID) throws ResourceNotFoundException {
		Collection<ProductPricePair> c = new ArrayList<>();
		
		for (Pair<Integer, Integer> p : mainDB.getAllProductsAndPricesInStore(storeID)) {
			c.add(new ProductPricePair(p));
		}
		return c;
	}
	
	///////////////////////////////////////////////////
	// More getters
	///////////////////////////////////////////////////
	// 14
	@GET
	@Path("/products/{productID}/average_rating")
	public MyNumber getAverageRatingOfProduct(@PathParam("productID") int productID) throws ResourceNotFoundException {
		return new MyNumber(mainDB.getAverageRatingOfProduct(productID));
	}
	
	///////////////////////////////////////////////////
	// Link store and product
	///////////////////////////////////////////////////
	// 15
	@PUT
	@Path("/products/{productID}/stores")
	public StorePricePair linkStoreAndProduct_fromProduct(
			@PathParam("productID") int productID, StorePricePair storePricePair) throws ResourceNotFoundException {
		mainDB.linkStoreAndProduct(productID, storePricePair.storeID, storePricePair.priceOfProduct);
		return storePricePair;
	}
	
	// 15
	@PUT
	@Path("/stores/{storeID}/products")
	public ProductPricePair linkStoreAndProduct_fromStore(
			@PathParam("storeID") int storeID, ProductPricePair productPricePair) throws ResourceNotFoundException {
		mainDB.linkStoreAndProduct(productPricePair.productID, storeID, productPricePair.priceOfProduct);
		return productPricePair;
	}
		
	///////////////////////////////////////////////////
	// User's cart
	///////////////////////////////////////////////////
//	// 16
//	@PUT
//	@Path("/users/{userID}/cart")
//	public ProductStorePair addToCart(@PathParam("userID") int userID, ProductStorePair productStorePair) throws ResourceNotFoundException {
//		mainDB.addToCart(userID, productStorePair.productID, productStorePair.storeID);
//		return productStorePair;
//	}
	
	// 17
	@DELETE
	@Path("/users/{userID}/cart")
	public void deleteFromCart(@PathParam("userID") int userID, 
			@QueryParam("productID") Integer productID,
			@QueryParam("storeID") Integer storeID) throws ResourceNotFoundException {
		if (productID != null && storeID != null)
			mainDB.deleteFromCart(userID, productID, storeID);
		else
			throw new DatabaseException("must have 'productID' and 'storeID' in the query params");
	}
	
//	// 18
//	@PUT
//	@Path("/users/{userID}/cart/pay")
//	public MyNumber payForUserCart(@PathParam("userID") int userID) throws ResourceNotFoundException {
//		return new MyNumber(mainDB.payForUserCart(userID));
//	}
	
	// 16 + 18
	@PUT
	@Path("/users/{userID}/cart")
	public Response payOrAdd(@PathParam("userID") int userID, 
			@QueryParam("pay") Boolean pay,
			@QueryParam("productID") Integer productID,
			@QueryParam("storeID") Integer storeID) throws ResourceNotFoundException, DatabaseException {
		
		if (pay != null && pay == true)
			return Response.status(Status.OK).entity(new MyNumber(mainDB.payForUserCart(userID))).build();
		else if (productID != null && storeID != null) {
			mainDB.addToCart(userID, productID, storeID);
			return Response.status(Status.OK).entity(
					new ProductStorePair(new Pair<Integer, Integer>(productID, storeID))).build();
		}
		throw new DatabaseException("must have a boolean 'pay' query param OR send a ProductStorePair");
	}
	
	// 19
	@GET
	@Path("/users/{userID}/cart")
	public Collection<ProductStorePair> getProductIDsAndStoreIDsFromCart(@PathParam("userID") int userID) throws ResourceNotFoundException {
		Collection<ProductStorePair> c = new ArrayList<>();
		
		for (Pair<Integer, Integer> p : mainDB.getProductIDsAndStoreIDsFromCart(userID)) {
			c.add(new ProductStorePair(p));
		}
		return c;
	}
	
	// 20
	@GET
	@Path("/users/{userID}/history")
	public Collection<ProductStorePair> getProductIDsAndStoreIDsBought(@PathParam("userID") int userID) throws ResourceNotFoundException {
		Collection<ProductStorePair> c = new ArrayList<>();
		
		for (Pair<Integer, Integer> p : mainDB.getProductIDsAndStoreIDsBought(userID)) {
			c.add(new ProductStorePair(p));
		}
		return c;
	}
	
}

