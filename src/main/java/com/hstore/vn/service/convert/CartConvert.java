package com.hstore.vn.service.convert;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hstore.vn.dto.response.CartResponse;
import com.hstore.vn.entity.Cart;


@Service
public class CartConvert {
	
	@Autowired
	public ProductConvert productConvert;
	

	public CartResponse cartConvertToCartResponse(Cart cart) {
		
		if(cart == null) {
			return null;
		}
		
		CartResponse cartResponse = new CartResponse(
				cart.getId(),
				productConvert.productsConverToProductsResponse(cart.getProducts())
				);
		return cartResponse;
	}

}
