package com.hstore.vn.payload.converter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hstore.vn.entity.Cart;
import com.hstore.vn.payload.response.CartResponse;
import com.hstore.vn.service.CartService;


@Service
public class CartConvert {
	
	@Autowired
	public ProductConvert productConvert;
	
	@Autowired
	public CartService cartService;
	
	public CartResponse cartConvertToCartResponse(Cart cart) {
		
		if(cart == null) {
			return null;
		}
		
		CartResponse cartResponse = new CartResponse(
				cart.getId(),
				productConvert.productsConverToProductsResponse(cartService.getProductsInCart(cart.getId()))
				);
		return cartResponse;
	}

}
