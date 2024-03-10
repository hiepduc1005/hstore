package com.hstore.vn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hstore.vn.dao.WishListDao;
import com.hstore.vn.entity.WishList;
import com.hstore.vn.service.WishListService;

@Service
public class DefaultWishListService implements WishListService{
	
	@Autowired
	public WishListDao wishListDao;

	@Override
	public WishList getWishListByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return wishListDao.getWishListByUserId(userId);
	}

	@Override
	public void addProductToWishList(Integer productId) {
		// TODO Auto-generated method stub
		wishListDao.addProductToWishList(productId);
	}

	@Override
	public void deleteProductToWishList(Integer productId) {
		// TODO Auto-generated method stub
		wishListDao.deleteProductToWishList(productId);
	}

	@Override
	public void createWishList(WishList wishList) {
		// TODO Auto-generated method stub
		wishListDao.createWishList(wishList);
	}

	@Override
	public WishList findWishListById(Integer wishListId) {
		// TODO Auto-generated method stub
		return wishListDao.findWishListById(wishListId);
	}

	@Override
	public void updateWishList(WishList wishList) {
		// TODO Auto-generated method stub
		wishListDao.updateWishList(wishList);
	}

}
