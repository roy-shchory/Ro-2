package com.rest.server.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "number")
public class MyNumber {

	@XmlElement(name = "value")
	public double num;
	
	public MyNumber() {}

	public MyNumber(double num) {this.num = num;}
}
