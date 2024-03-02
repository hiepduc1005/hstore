package com.hstore.vn.payload.response;

import java.util.List;



public class PurchaseResponse {
	
	public Integer id;
	
	public UserResponse user;
	
	public List<ProductResponse> products;
	
	public PurchaseStatusResponse purchaseStatus;

	public String localDateTime;

	public PurchaseResponse(Integer id, UserResponse user, List<ProductResponse> products, PurchaseStatusResponse purchaseStatus,
			String localDateTime) {
		this.id = id;
		this.user = user;
		this.products = products;							
		this.purchaseStatus = purchaseStatus;
		this.localDateTime = localDateTime;
	}
}
