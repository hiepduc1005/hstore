package com.hstore.vn.controller;

import javax.swing.RepaintManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hstore.vn.entity.Cart;
import com.hstore.vn.payload.ProductDto;
import com.hstore.vn.payload.response.ApiResponse;
import com.hstore.vn.service.CartService;
import com.hstore.vn.service.UserService;

@RestController
@RequestMapping("api/v1")
public class CartController {
	
	@Autowired
	public CartService cartService;
	
	@Autowired
	public UserService userService;
	
	@GetMapping("/cart-by-user")
	public ApiResponse<ResponseEntity<Cart>> getCartByUser(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		if(userService.getUserByEmail(username) == null) {
			throw new UsernameNotFoundException("Can not found user with email : " + username);
		}
		Cart cart = cartService.getCartByUserEmail(username);
		
		return new ApiResponse<ResponseEntity<Cart>>(
				"Get cart by user success !" ,new ResponseEntity<Cart>(cart,HttpStatus.OK),0);
	}
//	
//	@GetMapping("/cart/{cartId}")
//	public ApiResponse<ResponseEntity<Cart>> getCartById(@PathVariable Integer cartId){
//		u
//	}
//	
//	public ApiResponse<ResponseEntity<String>> addProductToCart(@RequestBody ProductDto productDto){
//		
//	}

}
