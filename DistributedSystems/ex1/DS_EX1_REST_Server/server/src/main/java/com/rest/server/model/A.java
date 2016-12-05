package com.rest.server.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class A {

	public String j;
	public int g;
	
	public A() {
		j="FF";g=14;
	}
	public A(int x){
		j="DD";g=x;
	}
	
	public int get() {return 5;}
}
