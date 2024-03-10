package com.hstore.vn.service;

import com.hstore.vn.entity.WishList;

public interface WishListService {
	
	    WishList getWishListByUserId(Integer id);
		
		void addProductToWishList(Integer productId);
		
		void deleteProductToWishList(Integer productId);
		
		void createWishList(WishList wishList);
		
		WishList findWishListById(Integer wishListId);
		
		void updateWishList(WishList wishList);

}
