package com.hstore.vn.dao;


import com.hstore.vn.entity.Cart;


public interface CartDao {
	
	Cart getCartByUserId(Integer id);
	
	void addProductToCart(Integer productId);
	
	void deleteProductToCart(Integer productId);
	
	Cart createCart(Cart cartDto);
	
	Cart findCartById(Integer cartId);
	
	void updateCart(Cart cartDto);
}
