package com.soap.server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String user_name;
	private int id;
	private Collection<Pair<Integer, Integer>> shopping_cart; // <product, store>
	private Collection<Pair<Integer, Integer>> history; // <product, store>
	
	public User(String user_name, int id){
		this.user_name = user_name;
		this.id = id;
		this.history = new ArrayList<>();
		this.shopping_cart = new ArrayList<>();
	}

	///////////////////////////////////////////////////
	// getters and setters
	///////////////////////////////////////////////////
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	///////////////////////////////////////////////////
	// get all
	///////////////////////////////////////////////////
	public Collection<Pair<Integer, Integer>> getAllItemsInCart() {
		return shopping_cart;
	}
	
	public Collection<Pair<Integer, Integer>> getAllItemsBought() {
		return history;
	}
	
	///////////////////////////////////////////////////
	// add
	///////////////////////////////////////////////////
	public void addToShoppingCart(int productID, int storeID){
		shopping_cart.add(new Pair<>(productID, storeID));
	}
	
	///////////////////////////////////////////////////
	// delete
	///////////////////////////////////////////////////
	public void deleteFromShoppingCart(int productID, int storeID){
		shopping_cart.remove(new Pair<>(productID, storeID));
	}
	
	///////////////////////////////////////////////////
	// history
	///////////////////////////////////////////////////
	public boolean wasTheProductBought(int productID) {
		for (Pair<Integer, Integer> pair : history) {
			if(pair.getLeft() == productID)
				return true;
		}
		return false;
	}
	
	public void payCart() {
		history.addAll(shopping_cart);
		shopping_cart.clear();
	}
	
	///////////////////////////////////////////////////
	// override
	///////////////////////////////////////////////////
	@Override
	public String toString() {
		return "user " + id + " " + user_name;
	}
	
}
