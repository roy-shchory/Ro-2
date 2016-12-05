package com.rest.server.model;

import com.rest.server.exceptions.DatabaseException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MainDB {	
	private Map<Integer, User> users;
	private int newUserID;
	
	private Map<Integer, Store> stores;
	private int newStoreID;
	
	private Map<Integer, Product> products;
	private int newProductID;

	public MainDB() {
		users = new HashMap<Integer, User>();
		stores = new HashMap<Integer, Store>();
		products = new HashMap<Integer, Product>();
		
		newUserID = 0;
		newStoreID = 0;
		newProductID = 0;
	}
	
	///////////////////////////////////////////////////
	// Add
	///////////////////////////////////////////////////
	// 1
	public User addNewUser(String name) {
		User user = new User(name, ++newUserID);
		users.put(user.getId(), user);
		return user;
	}
	
	// 1
	public Store addNewStore(String name, String phoneNumber) {
		Store store = new Store(name, ++newStoreID, phoneNumber);
		stores.put(store.getId(), store);
		return store;
	}
	
	// 1
	public Product addNewProduct(String name, String category, String description) {
		Product product = new Product(++newProductID, name, category, description);
		products.put(product.getId(), product);
		return product;
	}
	
	// 1
	public CustomerReview addNewCustomerReview(int productID, int rating, String review) throws DatabaseException  {
		Product product = getProductByID(productID);
		return product.addNewCustomerReview(rating, review);
	}
	
	///////////////////////////////////////////////////
	// GetByID
	///////////////////////////////////////////////////
	// 2
	public User getUserByID(int id) throws DatabaseException {
		User user = users.get(id);
		if(user == null)
			throw DatabaseException.generateInvalidIdError("User", id);
		return user;
	}
	
	// 2
	public Store getStoreByID(int id) throws DatabaseException {
		Store store = stores.get(id);
		if(store == null)
			throw DatabaseException.generateInvalidIdError("Store", id);
		return store;
	}
	
	// 2
	public Product getProductByID(int id) throws DatabaseException {
		Product product = products.get(id);
		if(product == null)
			throw DatabaseException.generateInvalidIdError("Product", id);
		return product;
	}
	
	// 2
	public CustomerReview getCustomerReviewByID(int productID, int reviewID) throws DatabaseException {
		Product product = getProductByID(productID);
		CustomerReview customerReview = product.getCustomerReviewByID(reviewID);
		
		if(customerReview == null)
			throw DatabaseException.generateInvalidIdError("Customer Review", reviewID);
		return customerReview;
	}
	
	///////////////////////////////////////////////////
	// Update
	///////////////////////////////////////////////////
	// 3
	public User updateUser(int id, String name) throws DatabaseException {
		User user = getUserByID(id);
		user.setUser_name(name);
		return user;
	}
	
	// 3
	public Store updateStore(int id, String name, String phoneNumber) throws DatabaseException {
		Store store = getStoreByID(id);
		store.setName(name);
		store.setPhone_number(phoneNumber);
		return store;
	}
	
	// 3
	public Product updateProduct(int id, String name, String category, String description) throws DatabaseException {
		Product product = getProductByID(id);
		product.setName(name);
		product.setCategory(category);
		product.setDescription(description);
		return product;
	}
	
	// 3
	public CustomerReview updateCustomerReview(int productID, int reviewID, int rating, String review) throws DatabaseException  {
		CustomerReview customerReview = getCustomerReviewByID(productID, reviewID);
		customerReview.setRating(rating);
		customerReview.setReview(review);
		return customerReview;
	}	
	
	///////////////////////////////////////////////////
	// Delete
	///////////////////////////////////////////////////
	// 4
	public void deleteProduct(int productID) throws DatabaseException {
		getProductByID(productID); // throws exception if productID is invalid
		
		for (Store store: stores.values()) {
			store.deleteProductById(productID);
		}
		
		products.remove(productID);
	}
	
	// 5
	public void deleteStore(int storeID) throws DatabaseException {
		getStoreByID(storeID); // throws exception if storeID is invalid
		
		for (Product product : products.values()) {
			product.deleteStoreById(storeID);
		}
		
		stores.remove(storeID);
	}
	
	// 6
	public void deleteUser(int userID) throws DatabaseException {
		getUserByID(userID); // throws exception if userID is invalid
		users.remove(userID);
	}
	
	// 7
	public void deleteCustomerReview(int productID, int reviewID) throws DatabaseException {
		getCustomerReviewByID(productID, reviewID); // throws exception if productID or reviewID are invalid
		
		Product product = getProductByID(productID);
		product.deleteReviewById(reviewID);
	}
	
	///////////////////////////////////////////////////
	// GetAll
	///////////////////////////////////////////////////
	// 8
	public Collection<Product> getAllProductsByCategory(String category) {
		Collection<Product> list = new ArrayList<>();
		for (Product product : products.values()) {
			if(product.getCategory().equals(category))
				list.add(product);
		}
		return list;
	}
	
	// 8
	public Collection<Product> getAllProductsByMaxPrice(int maxPrice) {
		Collection<Product> list = new ArrayList<>();
		for (Product product : products.values()) {
			Collection<Integer> prices = product.getAllStoresAndPrices().values();
			if (prices.isEmpty())
				continue;
			if (Collections.min(prices) <= maxPrice)
				list.add(product);
		}
		return list;
	}
	
	// 9
	public Collection<Store> getAllStores() {
		return stores.values();
	}
	
	// 10
	public Collection<CustomerReview> getAllCustomerReviews(int productID) throws DatabaseException {
		return getProductByID(productID).getAllReviews();
	}
	
	// 11
	public Collection<User> getAllUsers() {
		return users.values();
	}
	
	// 12
	public Collection<Pair<Integer, Integer>> getAllStoresAndPricesBySpecificProduct(int productID) throws DatabaseException {
		return mapToCollection(getProductByID(productID).getAllStoresAndPrices());
	}
	
	// 13
	public Collection<Pair<Integer, Integer>> getAllProductsAndPricesInStore(int storeID) throws DatabaseException {
		return mapToCollection(getStoreByID(storeID).getProductsWithPrices());
	}
	
	///////////////////////////////////////////////////
	// More getters
	///////////////////////////////////////////////////
	// 14
	public double getAverageRatingOfProduct(int productID) throws DatabaseException {
		Product product = getProductByID(productID);
		
		Double avgRating = product.getAverageRating();
		if (avgRating == null)
			throw new DatabaseException("There are no customer reviews for the product ID: " + productID);
		
		return avgRating;
	}
	
	///////////////////////////////////////////////////
	// Link store and product
	///////////////////////////////////////////////////
	// 15
	public void linkStoreAndProduct(int storeID, int productID, int priceInStore) throws DatabaseException {
		Store store = getStoreByID(storeID);
		Product product = getProductByID(productID);
		
		store.addProductWithPrice(productID, priceInStore);
		product.addStoreWithPrice(storeID, priceInStore);
	}
	
	private int checkIfProductIsLinkedToStore(int productID, int storeID) throws DatabaseException {
		getProductByID(productID);
		Store store = getStoreByID(storeID);
		
		Integer price = store.getProductPriceByID(productID); 
		if(price == null)
			throw new DatabaseException("product (" + productID +") is not linked to the store (" + storeID + ")");
		
		return price;
	}
	
	///////////////////////////////////////////////////
	// User's cart
	///////////////////////////////////////////////////
	// 16
	public void addToCart(int userID, int productID, int storeID) throws DatabaseException {
		User user = getUserByID(userID);
		checkIfProductIsLinkedToStore(productID, storeID);
		
		user.addToShoppingCart(productID, storeID);
	}
	
	// 17
	public void deleteFromCart(int userID, int productID, int storeID) throws DatabaseException {
		getUserByID(userID).deleteFromShoppingCart(productID, storeID);
	}
	
	// 18
	public int payForUserCart(int userID) throws DatabaseException {
		User user = getUserByID(userID);
		
		int cost = 0;
		for (Pair<Integer, Integer> pair : user.getAllItemsInCart()) {
			cost += checkIfProductIsLinkedToStore(pair.getLeft(), pair.getRight());
		}
		
		user.payCart();
		return cost;
	}
	
	// 19
	public Collection<Pair<Integer, Integer>> getProductIDsAndStoreIDsFromCart(int userID) throws DatabaseException {
		return getUserByID(userID).getAllItemsInCart();
	}
	
	// 20
	public Collection<Pair<Integer, Integer>> getProductIDsAndStoreIDsBought(int userID) throws DatabaseException {
		return getUserByID(userID).getAllItemsBought();
	}
	
	// 21
	public Collection<Integer> getAllUserIDsThatBoughtTheProduct(int productID) throws DatabaseException {
		getProductByID(productID);
		
		Collection<Integer> usersIdList = new ArrayList<>();		
		for (User user : users.values()) {
			if(user.wasTheProductBought(productID))
				usersIdList.add(user.getId());
		}
		
		return usersIdList;
	}
	
	
	private <K, V> Collection<Pair<K, V>> mapToCollection(Map<K, V> m) {
		Collection<Pair<K, V>> c = new ArrayList<>();
		for (Map.Entry<K, V> e : m.entrySet()) {
			c.add(new Pair<K, V>(e.getKey(), e.getValue()));
		}
		return c;
	}
}
