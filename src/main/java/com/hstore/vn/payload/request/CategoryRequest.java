package com.hstore.vn.payload.request;

public class CategoryRequest {
	
	public String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CategoryRequest(String name) {
		
		
		this.name = name;
	}
	public CategoryRequest() {
		
	}
	
	
}
