package com.rest.server.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "product_store")
public class ProductStorePair {	
	@XmlElement(name = "product_id", required = true)
	public int productID;
	
	@XmlElement(name = "store_id", required = true)
	public int storeID;
	
	public ProductStorePair() {}
	
	public ProductStorePair(Pair<Integer, Integer> p) {
		productID = p.getLeft();
		storeID = p.getRight();
	}

}
