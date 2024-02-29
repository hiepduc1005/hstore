package com.hstore.vn.service;

import java.math.BigDecimal;
import java.util.List;

import com.hstore.vn.entity.Cart;
import com.hstore.vn.entity.Product;



public interface CartService {
	
	Integer getNumbersOfProductInCart(Integer cartId);
	
	BigDecimal getTotalPriceInCartByCartId(Integer cartId);
	
	List<Product> getProductsInCart(Integer cartId);
	
	void updateCart(Cart cart);
	
    void addProductToCart(Integer productId);
	
	void deleteProductToCart(Integer productId);
	
	Cart createCart(Cart cartDto);
	
	Cart getCartByUserEmail(String email);
}
