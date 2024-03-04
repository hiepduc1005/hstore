package com.hstore.vn.payload.converter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hstore.vn.entity.Cart;
import com.hstore.vn.payload.response.CartResponse;


@Service
public class CartConvert {
	
	@Autowired
	public ProductConvert productConvert;
	
	public CartResponse cartConvertToCartResponse(Cart cart) {
		CartResponse cartResponse = new CartResponse(
				cart.getId(),
				productConvert.productsConverToProductsResponse(cart.getProducts())
				);
		return cartResponse;
	}

}
