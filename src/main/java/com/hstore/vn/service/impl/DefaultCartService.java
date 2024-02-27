package com.hstore.vn.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hstore.vn.dao.CartDao;
import com.hstore.vn.payload.CartDto;
import com.hstore.vn.payload.ProductDto;
import com.hstore.vn.payload.converter.CartConvert;
import com.hstore.vn.service.CartService;


@Service
public class DefaultCartService implements CartService{
	
	@Autowired
	public CartDao cartDao;
	
	@Autowired
	public CartConvert cartConvert;
	
	@Override
	public Integer getNumbersOfProductInCart(Integer userId) {
		CartDto cartDto = cartDao.getCartByUserId(userId);
		return cartDto.getProducts().size();
	}

	@Override
	public BigDecimal getTotalPriceInCartByUserId(Integer id) {
		List<ProductDto> productDtos = getProductsInCart(id);
		BigDecimal res = BigDecimal.ZERO;
		if(productDtos != null) {
			for(ProductDto productDto : productDtos) {
			res.add(productDto.getPrice());
		}
	}
		
		
		return res;
	}

	@Override
	public List<ProductDto> getProductsInCart(Integer userId) {
		CartDto cartDto = cartDao.getCartByUserId(userId);
		return cartDto.getProducts();
	}

}
