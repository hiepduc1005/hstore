package com.hstore.vn.payload.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hstore.vn.entity.Cart;
import com.hstore.vn.entity.impl.DefaultCart;
import com.hstore.vn.payload.CartDto;


@Service
public class CartConvert {
	
	@Autowired
	public ProductConvert productConvert;
	
	public Cart cartDtoConvertToCart(CartDto cartDto) {
		Cart cart = new DefaultCart();
		
		cart.setProducts(productConvert.productsDtoConvertToProducts(cartDto.getProducts()));
		cart.setUserId(cartDto.getUserId());
		
		return cart;
	}
	
	public CartDto cartConvertToCartDto(Cart cart) {
		CartDto cartDto = new CartDto();
		
		cartDto.setProducts(productConvert.productsConvertToProductsDto(cart.getProducts()));
		cartDto.setUserId(cart.getUserId());
		
		return cartDto;
	}

}
