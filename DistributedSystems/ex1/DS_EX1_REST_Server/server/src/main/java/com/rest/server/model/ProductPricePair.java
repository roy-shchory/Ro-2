package com.rest.server.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "product_price")
public class ProductPricePair {	
	@XmlElement(name = "product_id", required = true)
	public int productID;
	
	@XmlElement(name = "product_price", required = true)
	public int priceOfProduct;
	
	public ProductPricePair() {}
	
	public ProductPricePair(Pair<Integer, Integer> p) {
		productID = p.getLeft();
		priceOfProduct = p.getRight();
	}

}
