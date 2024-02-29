package com.hstore.vn.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hstore.vn.dao.CartDao;
import com.hstore.vn.entity.Cart;
import com.hstore.vn.entity.Product;
import com.hstore.vn.payload.CartDto;
import com.hstore.vn.payload.ProductDto;
import com.hstore.vn.payload.converter.CartConvert;
import com.hstore.vn.payload.converter.ProductConvert;
import com.hstore.vn.service.CartService;


@Service
public class DefaultCartService implements CartService{
	
	@Autowired
	public CartDao cartDao;
	
	@Autowired
	public CartConvert cartConvert;
	
	@Autowired
	public ProductConvert productConvert;
	
	@Override
	public Integer getNumbersOfProductInCart(Integer cartId) {
		CartDto cartDto = cartDao.findCartById(cartId);
		return cartDto.getProducts().size();
	}

	@Override
	public BigDecimal getTotalPriceInCartByCartId(Integer id) {
		CartDto cartDto = cartDao.findCartById(id);
		List<ProductDto> productDtos = cartDto.getProducts();
		BigDecimal res = BigDecimal.ZERO;
		if(productDtos != null) {
			for(ProductDto productDto : productDtos) {
			res.add(productDto.getPrice());
		}
	}
		
		
		return res;
	}

	@Override
	public List<Product> getProductsInCart(Integer cartId) {
		CartDto cartDto = cartDao.findCartById(cartId);
		return productConvert.productsDtoConvertToProducts(cartDto.getProducts());
	}

	@Override
	public void updateCart(Cart cart) {
		cartDao.updateCart(cartConvert.cartConvertToCartDto(cart));
	}

	@Override
	public void addProductToCart(Integer productId) {
		cartDao.addProductToCart(productId);
	}

	@Override
	public void deleteProductToCart(Integer productId) {
		// TODO Auto-generated method stub
		cartDao.deleteProductToCart(productId);
	}

	@Override
	public Cart createCart(Cart cartDto) {
		// TODO Auto-generated method stub
		return cartConvert.cartDtoConvertToCart(cartDao.createCart(cartConvert.cartConvertToCartDto(cartDto)));
	}

	@Override
	public Cart getCartByUserEmail(String email) {
		return cartConvert.cartDtoConvertToCart(cartDao.getCartByUserEmail(email));
	}

}
