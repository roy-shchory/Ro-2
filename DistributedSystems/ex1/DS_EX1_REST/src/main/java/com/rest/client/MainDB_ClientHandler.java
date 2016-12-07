package com.rest.client;

import com.rest.server.exceptions.ErrorMessage;
import com.rest.server.model.CustomerReview;
import com.rest.server.model.MyNumber;
import com.rest.server.model.Pair;
import com.rest.server.model.Product;
import com.rest.server.model.ProductPricePair;
import com.rest.server.model.ProductStorePair;
import com.rest.server.model.Store;
import com.rest.server.model.StorePricePair;
import com.rest.server.model.User;

import java.util.Collection;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class MainDB_ClientHandler {
	private WebTarget users;
	private WebTarget products;
	private WebTarget stores;
	
	///////////////////////////////////////////////////
	// WebTarget
	///////////////////////////////////////////////////
	private WebTarget getUserPath(int id) {
		return users.path(Integer.toString(id));
	}
	private WebTarget getProductPath(int id) {
		return products.path(Integer.toString(id));
	}
	private WebTarget getStorePath(int id) {
		return stores.path(Integer.toString(id));
	}
	private WebTarget getCustomerReviewPath(int productID, int reviewID) {
		return getProductPath(productID).path("customerReviews").path(Integer.toString(reviewID));
	}
	
	private WebTarget getCustomerReviewsPath(int productID) {
		return getProductPath(productID).path("customerReviews");
	}
	
	private WebTarget getUserCartPath(int userID) {
		return getUserPath(userID).path("cart");
	}
	private WebTarget getUserHistoryPath(int userID) {
		return getUserPath(userID).path("history");
	}
	
	private WebTarget getProductsInStorePath(int storeID) {
		return getStorePath(storeID).path("products");
	}
	private WebTarget getStoresInProductPath(int productID) {
		return getProductPath(productID).path("stores");
	}
	
	private WebTarget getAvgPath(int productID) {
		return getProductPath(productID).path("average_rating");
	}
	
	///////////////////////////////////////////////////
	// Web Errors
	///////////////////////////////////////////////////
	private void assertError(Response r) throws ServerException {
		int errorCode = r.getStatus(); 
		if (errorCode >= 400) {
			ErrorMessage e = r.readEntity(ErrorMessage.class);
			if (e.getErrorCode() == 0)
				throw new ServerException(new ErrorMessage("Internal Error", errorCode));
			throw new ServerException(e);
		}
	}
	
	///////////////////////////////////////////////////
	// Web requests
	///////////////////////////////////////////////////
	private <T> T postMe(WebTarget webTarget, T objToSend, Class<T> objClass) throws ServerException {
		Response r = webTarget.request().post(Entity.json(objToSend));
		assertError(r);
		return r.readEntity(objClass);
	}
	
	private <T> T getMe(WebTarget webTarget, Class<T> objClass) throws ServerException {
		Response r = webTarget.request(MediaType.APPLICATION_JSON).get();
		assertError(r);
		return r.readEntity(objClass);
	}
	
	private <T> T getMeGeneric(WebTarget webTarget, GenericType<T> genericType) throws ServerException {
		Response r = webTarget.request(MediaType.APPLICATION_JSON).get();
		assertError(r);
		return r.readEntity(genericType);
	}
	
	private void deleteMe(WebTarget webTarget) throws ServerException {
		Response r = webTarget.request().delete();
		assertError(r);
	}
	
	private <T> T putMe(WebTarget webTarget, T objToSend, Class<T> objClass) throws ServerException {
		Response r = webTarget.request().put(Entity.json(objToSend));
		assertError(r);
		return r.readEntity(objClass);
	}
	
	///////////////////////////////////////////////////
	// C'tor
	///////////////////////////////////////////////////	
	public MainDB_ClientHandler(String basePath) {
		Client client = ClientBuilder.newClient();
		WebTarget baseTarget = client.target(basePath);
		users = baseTarget.path("users");
		products = baseTarget.path("products");
		stores = baseTarget.path("stores");
	}
	
	///////////////////////////////////////////////////
	// Add
	///////////////////////////////////////////////////
	// 1
	public User addNewUser(String name) throws ServerException {
		User a = new User(name, 0);
		return postMe(users, a, User.class);
	}
	
	// 1
	public Store addNewStore(String name, String phoneNumber) throws ServerException {
		Store a = new Store(name, 0, phoneNumber);
		return postMe(stores, a, Store.class);
	}
	
	// 1
	public Product addNewProduct(String name, String category, String description) throws ServerException {
		Product a = new Product(0, name, category, description);
		return postMe(products, a, Product.class);
	}
	
	// 1
	public CustomerReview addNewCustomerReview(int productID, int rating, String review) throws ServerException  {
		CustomerReview a = new CustomerReview(0, productID, review, rating);
		return postMe(getCustomerReviewsPath(productID), a, CustomerReview.class);
	}
	
	///////////////////////////////////////////////////
	// GetByID
	///////////////////////////////////////////////////
	// 2
	public User getUserByID(int id) throws ServerException {
		return getMe(getUserPath(id), User.class);
	}
	
	// 2
	public Store getStoreByID(int id) throws ServerException {
		return getMe(getStorePath(id), Store.class);
	}
	
	// 2
	public Product getProductByID(int id) throws ServerException {
		return getMe(getProductPath(id), Product.class);
	}
	
	// 2
	public CustomerReview getCustomerReviewByID(int productID, int reviewID) throws ServerException {
		return getMe(getCustomerReviewPath(productID, reviewID), CustomerReview.class);
	}
	
	///////////////////////////////////////////////////
	// Update
	///////////////////////////////////////////////////
	// 3
	public User updateUser(int id, String name) throws ServerException {
		User a = new User(name, 0);
		return putMe(getUserPath(id), a, User.class);
	}
	
	// 3
	public Store updateStore(int id, String name, String phoneNumber) throws ServerException {
		Store a = new Store(name, 0, phoneNumber);
		return putMe(getStorePath(id), a, Store.class);
	}
	
	// 3
	public Product updateProduct(int id, String name, String category, String description) throws ServerException {
		Product a = new Product(0, name, category, description);
		return putMe(getProductPath(id), a, Product.class);
	}
	
	// 3
	public CustomerReview updateCustomerReview(int productID, int reviewID, int rating, String review) throws ServerException {
		CustomerReview a = new CustomerReview(0, productID, review, rating);
		return putMe(getCustomerReviewPath(productID, reviewID), a, CustomerReview.class);
	}	
	
	///////////////////////////////////////////////////
	// Delete
	///////////////////////////////////////////////////
	// 4
	public void deleteProduct(int productID) throws ServerException {
		deleteMe(getProductPath(productID));
	}
	
	// 5
	public void deleteStore(int storeID) throws ServerException {
		deleteMe(getStorePath(storeID));		
	}
	
	// 6
	public void deleteUser(int userID) throws ServerException {
		deleteMe(getUserPath(userID));
	}
	
	// 7
	public void deleteCustomerReview(int productID, int reviewID) throws ServerException {
		deleteMe(getCustomerReviewPath(productID, reviewID));
	}
	
	///////////////////////////////////////////////////
	// GetAll
	///////////////////////////////////////////////////
	// 8
	public Collection<Product> getAllProductsByCategory(String category) throws ServerException {
		return getMeGeneric(products.queryParam("category", category), new GenericType<Collection<Product>>() {});
	}
	
	// 8
	public Collection<Product> getAllProductsByMaxPrice(int maxPrice) throws ServerException {
		return getMeGeneric(products.queryParam("maxPrice", maxPrice), new GenericType<Collection<Product>>() {});
	}
	
	// 9
	public Collection<Store> getAllStores() throws ServerException {
		return getMeGeneric(stores, new GenericType<Collection<Store>>() {});
	}
	
	// 10
	public Collection<CustomerReview> getAllCustomerReviews(int productID) throws ServerException {
		return getMeGeneric(getCustomerReviewsPath(productID), new GenericType<Collection<CustomerReview>>() {});
	}
	
	// 11
	public Collection<User> getAllUsers() throws ServerException {
		return getMeGeneric(users, new GenericType<Collection<User>>() {});
	}
	
	// 12
	public Collection<StorePricePair> getAllStoresAndPricesBySpecificProduct(int productID) throws ServerException {
		return getMeGeneric(getStoresInProductPath(productID), new GenericType<Collection<StorePricePair>>() {});
	}
	
	// 13
	public Collection<ProductPricePair> getAllProductsAndPricesInStore(int storeID) throws ServerException {
		return getMeGeneric(getProductsInStorePath(storeID), new GenericType<Collection<ProductPricePair>>() {});
	}
	
	///////////////////////////////////////////////////
	// More getters
	///////////////////////////////////////////////////
	// 14
	public double getAverageRatingOfProduct(int productID) throws ServerException {
		return getMe(getAvgPath(productID), MyNumber.class).num;
	}
	
	///////////////////////////////////////////////////
	// Link store and product
	///////////////////////////////////////////////////
	// 15
	public void linkStoreAndProduct(int storeID, int productID, int priceInStore) throws ServerException {
		putMe(getProductsInStorePath(storeID), 
				new ProductPricePair(new Pair<Integer, Integer>(productID, priceInStore)),
				ProductPricePair.class);
	}
	
	///////////////////////////////////////////////////
	// User's cart
	///////////////////////////////////////////////////
	// 16
	public void addToCart(int userID, int productID, int storeID) throws ServerException {
		putMe(getUserCartPath(userID)
			.queryParam("productID", productID)
			.queryParam("storeID", storeID), null, ProductStorePair.class);
	}
	
	// 17
	public void deleteFromCart(int userID, int productID, int storeID) throws ServerException {
		Response r = getUserCartPath(userID)
			.queryParam("productID", productID)
			.queryParam("storeID", storeID)
			.request().delete();
		assertError(r);
	}
	
	// 18
	public int payForUserCart(int userID) throws ServerException {
		return (int)putMe(getUserCartPath(userID).queryParam("pay", true), null, MyNumber.class).num;
	}
	
	// 19
	public Collection<ProductStorePair> getProductIDsAndStoreIDsFromCart(int userID) throws ServerException {
		return getMeGeneric(getUserCartPath(userID), new GenericType<Collection<ProductStorePair>>() {});
	}
	
	// 20
	public Collection<ProductStorePair> getProductIDsAndStoreIDsBought(int userID) throws ServerException {
		return getMeGeneric(getUserHistoryPath(userID), new GenericType<Collection<ProductStorePair>>() {});
	}
	
	// 21
	public Collection<User> getAllUserIDsThatBoughtTheProduct(int productID) throws ServerException {
		return getMeGeneric(users.queryParam("productID", productID), new GenericType<Collection<User>>() {});
	}
}
