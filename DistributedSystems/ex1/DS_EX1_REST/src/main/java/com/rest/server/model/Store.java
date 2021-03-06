package com.rest.server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.rest.server.resources.MainDbResource;

@XmlRootElement(name = "store")
public class Store implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name = "name", required = true)
	public String name;
	
	@XmlElement(name = "id", required = false)
	public int id;
	
	@XmlElement(name = "phone", required = true)
	public String phone_number;
		
	private Map<Integer, Integer> productsAndPriceList = new HashMap<>();
	
	@XmlElement(name = "links", required = false)
	public List<Link> links  = new ArrayList<>();
	
	public Store() {}
	
	public Store(String name,int id,String phone_number){
		this.name = name;
		this.id = id;
		this.phone_number = phone_number;
	}

	///////////////////////////////////////////////////
	// getters and setters
	///////////////////////////////////////////////////
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	
	///////////////////////////////////////////////////
	// more getters
	///////////////////////////////////////////////////
	/**
	 * @return the price or null if product doesn't exist
	 */
	public Integer getProductPriceByID(int productID){
		return productsAndPriceList.get(productID);
	}
	
	public Map<Integer, Integer> getProductsWithPrices() {
		return productsAndPriceList;
	}
	
	///////////////////////////////////////////////////
	// delete
	///////////////////////////////////////////////////
	/**
	 * @return true if deleted, or false if product doesn't exist
	 */
	public boolean deleteProductById(int productID) {
		return productsAndPriceList.remove(productID) != null;
	}
	
	///////////////////////////////////////////////////
	// add
	///////////////////////////////////////////////////
	/**
	 * @return true if new, or false if updated an existing entry
	 */
	public boolean addProductWithPrice(int productID, int price) {
		return productsAndPriceList.put(productID, price) == null;
	}
	
	///////////////////////////////////////////////////
	// links
	///////////////////////////////////////////////////
	private UriBuilder makeBase(UriInfo uriInfo) {
		UriBuilder base = uriInfo.getBaseUriBuilder()
				.path(MainDbResource.class)
				.path("stores")
				.path(Integer.toString(this.getId()));
		return base;
	}
	public void setLinks(UriInfo uriInfo) {		
		links.add(new Link().setLink(makeBase(uriInfo).build().toString(), "self"));
		
		String uri = makeBase(uriInfo).path("products").build().toString();
		links.add(new Link().setLink(uri, "products"));   
	}
	
	///////////////////////////////////////////////////
	// overrides
	///////////////////////////////////////////////////
	@Override
	public boolean equals(Object obj) {
		return !(obj instanceof Store)?false:((Store)(obj)).getId()==this.id;
	}
	
	@Override
	public String toString() {
		return "store " + id + " " + name + " " + phone_number;
	}
	
}
