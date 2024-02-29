package com.hstore.vn.dao.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.hstore.vn.dao.CartDao;
import com.hstore.vn.dao.ProductDao;
import com.hstore.vn.dao.UserDao;
import com.hstore.vn.exception.cart.CartNotFoundException;
import com.hstore.vn.exception.product.NotFoundProductException;
import com.hstore.vn.exception.user.UserNotFoundException;
import com.hstore.vn.payload.CartDto;
import com.hstore.vn.payload.ProductDto;
import com.hstore.vn.payload.UserDto;

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
		
		UserDto userDto = userDao.getUserByEmail(username);
		if(userDto == null) {
			throw new UserNotFoundException("Can not found user with email : " + username);
		}
		CartDto cartDto = findCartById(userDto.getCart().getId());
		if(cartDto == null) {
			throw new CartNotFoundException("Can not found cart in user : " + userDto.getEmail());
		}
		
		List<ProductDto> productDtos = cartDto.getProducts();
		ProductDto productDto = productDao.getProductById(productId);
		productDtos.add(productDto);
		
		cartDto.setProducts(productDtos);
		
		updateCart(cartDto);
	}

	@Transactional
	@Override
	public void deleteProductToCart(Integer productId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		UserDto userDto = userDao.getUserByEmail(username);
		if(userDto == null) {
			throw new UserNotFoundException("Can not found user with email : " + username);
		}
		CartDto cartDto = findCartById(userDto.getCart().getId());
		if(cartDto == null) {
			throw new CartNotFoundException("Can not found cart in user : " + userDto.getEmail());
		}
		
		List<ProductDto> productDtos = cartDto.getProducts();
		if(productDtos == null) {
			throw new NotFoundProductException("Not found any product in cart");
		}
		ProductDto productDto = productDao.getProductById(productId);
		productDtos.remove(productDto);
		
		cartDto.setProducts(productDtos);
		
		updateCart(cartDto);
	}

	@Transactional
	@Override
	public CartDto createCart(CartDto cartDto) {
		return entityManager.merge(cartDto);
	}
	
	
	@Transactional
	@Override
	public void updateCart(CartDto cartDto) {
		 entityManager.merge(cartDto);
	}
	
	@Transactional
	@Override
	public CartDto findCartById(Integer cartId) {
		try {
			CartDto cartDto = entityManager.find(CartDto.class, cartId);
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
	public CartDto getCartByUserEmail(String email) {
		Query query = entityManager.createNativeQuery("SELECT cart_id FROM user WHERE email = :email",Integer.class);
		query.setParameter("email", email);
		Integer cartId = (Integer) query.getResultList().stream().findFirst().orElse(null);
		if(cartId == null) {
			throw new UserNotFoundException("Can not found user with email " + email);
		}
		return findCartById(cartId);
	}	


}
