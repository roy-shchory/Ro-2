package com.rest.server.model;

public class Link {
	private String link;
	private String rel;
	
	public String getLink() {
		return link;
	}
	public Link setLink(String link) {
		this.link = link;
		return this;
	}
	public Link setLink(String link, String rel) {
		this.link = link;
		this.rel = rel;
		return this;
	}
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	
	
}