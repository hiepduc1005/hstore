package com.hstore.vn.payload.request;

import java.util.List;


public class PurchaseRequest {
	
	public List<Integer> productsId;

	public List<Integer> getProducts() {
		return productsId;
	}

	public void setProducts(List<Integer> productsId) {
		this.productsId = productsId;
	}
	
}
