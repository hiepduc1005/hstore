package com.hstore.vn.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hstore.vn.dao.CartDao;
import com.hstore.vn.dao.UserDao;
import com.hstore.vn.entity.Cart;
import com.hstore.vn.entity.Product;
import com.hstore.vn.entity.User;
import com.hstore.vn.service.CartService;


@Service
public class DefaultCartService implements CartService{
	
	@Autowired
	public CartDao cartDao;
	
	@Autowired
	public UserDao userDao;
	
	
	
	@Override
	public Integer getNumbersOfProductInCart(Integer cartId) {
		Cart cartDto = cartDao.findCartById(cartId);
		return cartDto.getProducts().size();
	}

	

	@Override
	public List<Product> getProductsInCart(Integer cartId) {
		Cart cartDto = cartDao.findCartById(cartId);
		return cartDto.getProducts();
	}

	@Override
	public void updateCart(Cart cart) {
		cartDao.updateCart(cart);
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
	public Cart createCart(Cart cart) {
		// TODO Auto-generated method stub
		return cartDao.createCart(cart);
	}

	@Override 
	public Cart getCartByUserId(Integer id) {
		return cartDao.getCartByUserId(id);
	}

	@Override
	public Cart getCartById(Integer cartId) {
		// TODO Auto-generated method stub
		return cartDao.findCartById(cartId);
	}



	@Override
	public BigDecimal getTotalPriceInCartByAuthenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
	    User user = userDao.getUserByEmail(username);
		
		Cart cart = getCartByUserId(user.getId());
		List<Product> products = cart.getProducts();
		BigDecimal totalPrice = BigDecimal.ZERO;
		
		if(products.isEmpty()) {
			return totalPrice;
		}
		
		for(Product product : products) {
			totalPrice = totalPrice.add(product.getPrice());
		}
		return totalPrice;
	}

}
