package com.hstore.vn.service.convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hstore.vn.dto.response.WishListResponse;
import com.hstore.vn.entity.WishList;

@Service
public class WishListConvert {
	
	@Autowired
	public ProductConvert productConvert;
	
	public WishListResponse wishListConvertToWishListResponse(WishList wishList) {
		
		return new WishListResponse(
				wishList.getId(),
				productConvert.productsConverToProductsResponse(wishList.getProducts())
				);
	}
}
