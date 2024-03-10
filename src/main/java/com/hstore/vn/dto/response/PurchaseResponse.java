package com.hstore.vn.dto.response;

import java.util.List;



public class PurchaseResponse {
	
	public Integer id;
	
	public List<ProductResponse> products;
	
	public PurchaseStatusResponse purchaseStatus;
	
	public String address;

	public String localDateTime;

	public PurchaseResponse(Integer id, List<ProductResponse> products, PurchaseStatusResponse purchaseStatus,
			String address,String localDateTime) {
		this.id = id;
		this.products = products;							
		this.purchaseStatus = purchaseStatus;
		this.address = address;
		this.localDateTime = localDateTime;
	}
}
