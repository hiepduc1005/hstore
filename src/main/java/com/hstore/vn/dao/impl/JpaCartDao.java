package com.hstore.vn.dao.impl;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.hstore.vn.dao.CartDao;
import com.hstore.vn.dao.ProductDao;
import com.hstore.vn.dao.UserDao;
import com.hstore.vn.entity.Cart;
import com.hstore.vn.entity.Product;
import com.hstore.vn.entity.User;
import com.hstore.vn.exception.cart.CartNotFoundException;
import com.hstore.vn.exception.product.NotFoundProductException;
import com.hstore.vn.exception.user.UserNotFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class JpaCartDao implements CartDao{

	@Autowired
	public EntityManager entityManager;
	
	@Autowired
	public ProductDao productDao;
	
	@Autowired
	public UserDao userDao;
	
	
	
	
	

	@Transactional
	@Override
	public void addProductToCart(Integer productId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		User userDto = userDao.getUserByEmail(username);
		if(userDto == null) {
			throw new UserNotFoundException("Can not found user with email : " + username);
		}
		Cart cartDto = findCartById(userDto.getCart().getId());
		if(cartDto == null) {
			throw new CartNotFoundException("Can not found cart in user : " + userDto.getEmail());
		}
		
		List<Product> productDtos = cartDto.getProducts();

		Product productDto = productDao.getProductById(productId);
		if(productDto == null) {
			throw new NotFoundProductException("Can not found product with id : " + productId);
		}	
		
		productDtos.add(productDto);
		cartDto.setProducts(productDtos);
		

		updateCart(cartDto);
	}

	@Transactional
	@Override
	public void deleteProductToCart(Integer productId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		User userDto = userDao.getUserByEmail(username);
		if(userDto == null) {
			throw new UserNotFoundException("Can not found user with email : " + username);
		}
		Cart cartDto = findCartById(userDto.getCart().getId());
		if(cartDto == null) {
			throw new CartNotFoundException("Can not found cart in user : " + userDto.getEmail());
		}
		
		List<Product> productDtos = cartDto.getProducts();
		if(productDtos == null) {
			throw new NotFoundProductException("Not found any product in cart");
		}

		Product productDto = productDao.getProductById(productId);
		if(productDto == null) {
			throw new NotFoundProductException("Can not found product with id : " + productId);
		}
		productDtos.remove(productDto);
		
		cartDto.setProducts(productDtos);
		
		updateCart(cartDto);
	}

	@Transactional
	@Override
	public Cart createCart(Cart cartDto) {
		return entityManager.merge(cartDto);
	}
	
	
	@Transactional
	@Override
	public void updateCart(Cart cartDto) {
		 entityManager.merge(cartDto);
	}
	
	@Transactional
	@Override
	public Cart findCartById(Integer cartId) {
		try {
			Cart cartDto = entityManager.find(Cart.class, cartId);
			if(cartDto == null) {
				throw new CartNotFoundException("Can not found cart with id : " + cartId);
			}
			return cartDto;
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Cart id must be type int");
		}
		
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public Cart getCartByUserId(Integer id) {
		Query query = entityManager.createNativeQuery("SELECT * FROM cart WHERE user_id = :user_id",Cart.class);
		query.setParameter("user_id", id);
		Cart cart = (Cart) query.getResultList().stream().findFirst().orElse(null);
		if(cart == null) {
			throw new UserNotFoundException("Can not found user with id " + id);
		}
		return cart;
	}	


}
