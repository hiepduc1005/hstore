package com.hstore.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hstore.vn.dto.response.ApiResponse;
import com.hstore.vn.dto.response.WishListResponse;
import com.hstore.vn.entity.User;
import com.hstore.vn.entity.WishList;
import com.hstore.vn.service.UserService;
import com.hstore.vn.service.WishListService;
import com.hstore.vn.service.convert.WishListConvert;

@RestController
@RequestMapping(path = "/api/v1/wish-list")
public class WishListController {
	
	@Autowired
	public WishListService wishListService;
	
	@Autowired
	public UserService userService;
	
	@Autowired
	public WishListConvert wishListConvert;
	
	@GetMapping
	public ApiResponse<ResponseEntity<WishListResponse>> getWishListByUserAuthenticated(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = authentication.getName();
		
		User user = userService.getUserByEmail(userEmail);
		WishList wishList = wishListService.getWishListByUserId(user.getId());
		WishListResponse wishListResponse = wishListConvert.wishListConvertToWishListResponse(wishList);
		
		return new ApiResponse<ResponseEntity<WishListResponse>>(
				"Get wish list by user with email : " + userEmail + " success !" ,
				new ResponseEntity<WishListResponse>(wishListResponse,HttpStatus.OK), 0);
	}
	
	@GetMapping(path = "/{id}")
	public ApiResponse<ResponseEntity<WishListResponse>> getWishListById(@PathVariable Integer id){
		WishList wishList = wishListService.findWishListById(id);
		WishListResponse wishListResponse = wishListConvert.wishListConvertToWishListResponse(wishList);
		
		return new ApiResponse<ResponseEntity<WishListResponse>>(
				"Get wish list by id : " + id + " success !" ,
				new ResponseEntity<WishListResponse>(wishListResponse,HttpStatus.OK), 0);
	}
	
	@PostMapping(path = "/product/{productId}")
	public ApiResponse<ResponseEntity<?>> addProductToWishList(@PathVariable Integer productId){
		
		wishListService.addProductToWishList(productId);
		
		return new ApiResponse<ResponseEntity<?>>(
				"Add product id : " + productId + " success !" ,
				new ResponseEntity<>(HttpStatus.OK), 0);
	}
	
	@DeleteMapping(path = "/product/{productId}")
	public ApiResponse<ResponseEntity<?>> deleteProductToWishList(@PathVariable Integer productId){
		
		wishListService.deleteProductToWishList(productId);
		
		return new ApiResponse<ResponseEntity<?>>(
				"Delete product id : " + productId + " success !" ,
				new ResponseEntity<>(HttpStatus.OK), 0);
	}
	
}
