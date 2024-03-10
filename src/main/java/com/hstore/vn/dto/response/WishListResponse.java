package com.hstore.vn.dto.response;

import java.util.List;

public class WishListResponse {
	
	public Integer id;
	
	public List<ProductResponse> productResponses;


	public List<ProductResponse> getProductResponses() {
		return productResponses;
	}


	public WishListResponse(Integer id, List<ProductResponse> productResponses) {
		this.id = id;
		this.productResponses = productResponses;
	}


     
}

