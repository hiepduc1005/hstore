package com.hstore.vn.payload.request;

import java.util.List;


public class PurchaseRequest {
	
	public List<Integer> productsId;
	
	public String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Integer> getProducts() {
		return productsId;
	}

	public void setProducts(List<Integer> productsId) {
		this.productsId = productsId;
	}
	
}
