package com.hstore.vn.service;

import java.math.BigDecimal;
import java.util.List;

import com.hstore.vn.payload.ProductDto;



public interface CartService {
	
	Integer getNumbersOfProductInCart(Integer userId);
	
	BigDecimal getTotalPriceInCartByUserId(Integer userId);
	
	List<ProductDto> getProductsInCart(Integer userId);
	
}
