package com.hstore.vn.dto.request;

import java.util.List;

public class PurchaseRequestUpdate {
	
	public int id;
	
	public List<Integer> productsId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Integer> getProductsId() {
		return productsId;
	}

	public void setProductsId(List<Integer> productsId) {
		this.productsId = productsId;
	}
}
