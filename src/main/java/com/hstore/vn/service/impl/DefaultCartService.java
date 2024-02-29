package com.hstore.vn.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hstore.vn.dao.CartDao;
import com.hstore.vn.entity.Cart;
import com.hstore.vn.entity.Product;
import com.hstore.vn.payload.CartDto;
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

	@Override
	public Cart getCartById(Integer cartId) {
		// TODO Auto-generated method stub
		return cartConvert.cartDtoConvertToCart(cartDao.findCartById(cartId));
	}



	@Override
	public BigDecimal getTotalPriceInCartByAuthenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		Cart cart = getCartByUserEmail(username);
		List<Product> products = cart.getProducts();
		BigDecimal totalPrice = BigDecimal.ZERO;
		
		if(products.isEmpty()) {
			return totalPrice;
		}
		
		for(Product product : products) {
			totalPrice.add(product.getPrice());
		}
		
		return totalPrice;
	}

}
