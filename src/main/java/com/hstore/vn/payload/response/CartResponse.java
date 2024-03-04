package com.hstore.vn.payload.response;

import java.util.List;

public class CartResponse {
	public Integer cartId;
	
	public List<ProductResponse> products;

	public CartResponse(Integer cartId, List<ProductResponse> products) {
	
		this.cartId = cartId;
		this.products = products;
		
	}
}
