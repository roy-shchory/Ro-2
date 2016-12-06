package com.rest.server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name = "name", required = true)
	public String user_name;
	
	@XmlElement(name = "id", required = false)
	public int id;
	
	private Collection<Pair<Integer, Integer>> shopping_cart = new ArrayList<>(); // <product, store>
	private Collection<Pair<Integer, Integer>> history = new ArrayList<>(); // <product, store>
	
	public User() {}
	
	public User(String user_name, int id){
		this.user_name = user_name;
		this.id = id;
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
