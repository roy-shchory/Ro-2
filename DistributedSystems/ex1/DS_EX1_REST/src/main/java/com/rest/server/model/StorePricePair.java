package com.rest.server.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "store_price")
public class StorePricePair {	
	@XmlElement(name = "store_id", required = true)
	public int storeID;
	
	@XmlElement(name = "product_price", required = true)
	public int priceOfProduct;
	
	public StorePricePair() {}
	
	public StorePricePair(Pair<Integer, Integer> p) {
		storeID = p.getLeft();
		priceOfProduct = p.getRight();
	}
	
	@Override
	public String toString(){
		return this.storeID + " = " + this.priceOfProduct;
	}

}
