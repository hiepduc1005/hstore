package com.hstore.vn.dao;


import com.hstore.vn.payload.CartDto;

public interface CartDao {
	
	CartDto getCartByUserEmail(String email);
	
	void addProductToCart(Integer productId);
	
	void deleteProductToCart(Integer productId);
	
	CartDto createCart(CartDto cartDto);
	
	CartDto findCartById(Integer cartId);
	
	void updateCart(CartDto cartDto);
}
