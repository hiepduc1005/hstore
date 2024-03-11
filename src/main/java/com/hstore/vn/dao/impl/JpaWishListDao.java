package com.hstore.vn.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.hstore.vn.dao.ProductDao;
import com.hstore.vn.dao.UserDao;
import com.hstore.vn.dao.WishListDao;
import com.hstore.vn.entity.Product;
import com.hstore.vn.entity.User;
import com.hstore.vn.entity.WishList;
import com.hstore.vn.exception.product.NotFoundProductException;
import com.hstore.vn.exception.user.UserNotFoundException;
import com.hstore.vn.exception.wishlist.WishListNotFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class JpaWishListDao implements WishListDao{
	
	@Autowired
	private EntityManager em;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ProductDao productDao;

	@Transactional
	@Override
	public WishList getWishListByUserId(Integer id) {
		
		TypedQuery<WishList> typedQuery = em.createQuery("SELECT wl FROM wish_list wl WHERE wl.user.id = :userId" , WishList.class);
		
		typedQuery.setParameter("userId", id);
		
		WishList wishList = typedQuery.getResultList().stream().findFirst().orElse(null);
		if(wishList == null) {
			throw new WishListNotFoundException("Can not found wish list with id : " + id);
		}
		return wishList;
	}

	@Transactional
	@Modifying
	@Override
	public void addProductToWishList(Integer productId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		User userDto = userDao.getUserByEmail(username);
		if(userDto == null) {
			throw new UserNotFoundException("Can not found user with email : " + username);
		}
		WishList wishList = userDto.getWishList();
		if(wishList == null) {
			throw new WishListNotFoundException("Can not found wishlist in user : " + userDto.getEmail());
		}
		
		List<Product> productDtos = wishList.getProducts();

		Product productDto = productDao.getProductById(productId);
		if(productDto == null) {
			throw new NotFoundProductException("Can not found product with id : " + productId);
		}	
		
		productDtos.add(productDto);
		wishList.setProducts(productDtos);
		

		updateWishList(wishList);
		
	}

	@Transactional
	@Modifying
	@Override
	public void deleteProductToWishList(Integer productId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		User userDto = userDao.getUserByEmail(username);
		if(userDto == null) {
			throw new UserNotFoundException("Can not found user with email : " + username);
		}
		WishList wishList = userDto.getWishList();
		if(wishList == null) {
			throw new WishListNotFoundException("Can not found wishlist in user : " + userDto.getEmail());
		}
		
		List<Product> productDtos = wishList.getProducts();

		Product productDto = productDao.getProductById(productId);
		if(productDto == null) {
			throw new NotFoundProductException("Can not found product with id : " + productId);
		}	
		
		productDtos.remove(productDto);
		wishList.setProducts(productDtos);
		

		updateWishList(wishList);
		
	}

	@Transactional
	@Override
	public void createWishList(WishList wishList) {
		em.persist(wishList);
	}

	@Transactional
	@Override
	public WishList findWishListById(Integer wishListId) {
		if(wishListId == null || wishListId < 1) {
			throw new IllegalArgumentException("Wish list id must be type int");
		}
		
		WishList wishList = em.find(WishList.class,wishListId);
		
		if(wishList == null) {
			throw new WishListNotFoundException("Can not found wish list with id : " + wishListId);
		}
		
		return wishList;
	}

	@Transactional
	@Modifying
	@Override
	public void updateWishList(WishList wishList) {
		if(wishList == null) {
			throw new WishListNotFoundException("Can not found wish list");
		}
		
		em.merge(wishList);
	}

}
