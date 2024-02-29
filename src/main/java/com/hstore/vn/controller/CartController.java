package com.hstore.vn.controller;

import java.math.BigDecimal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hstore.vn.entity.Cart;
import com.hstore.vn.entity.Product;
import com.hstore.vn.exception.product.NotFoundProductException;
import com.hstore.vn.payload.response.ApiResponse;
import com.hstore.vn.payload.response.TotalPriceResponse;
import com.hstore.vn.service.CartService;
import com.hstore.vn.service.ProductService;
import com.hstore.vn.service.UserService;

@RestController
@RequestMapping("api/v1")
public class CartController {
	
	@Autowired
	public CartService cartService;
	
	@Autowired
	public UserService userService;
	
	@Autowired
	public ProductService productService;
	
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
	
	@GetMapping("/cart/{cartId}")
	public ApiResponse<ResponseEntity<Cart>> getCartById(@PathVariable Integer cartId){
		Cart cart = cartService.getCartById(cartId);
		
		return new ApiResponse<ResponseEntity<Cart>>(
				"Get cart with id : " + cartId + " success !",new ResponseEntity<Cart>(cart , HttpStatus.OK),0);
		
	}
	
	@GetMapping("/cart/total-price")
	public ApiResponse<ResponseEntity<TotalPriceResponse>> getTotalPrice(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		BigDecimal totalPrice = cartService.getTotalPriceInCartByAuthenticatedUser();
		
		return new ApiResponse<ResponseEntity<TotalPriceResponse>>(
				"Get total price in user with email : + " + username + " success !",
				new ResponseEntity<TotalPriceResponse>(new TotalPriceResponse(totalPrice),
				HttpStatus.OK),0);
	}
	
	
	@PostMapping("/cart/product/{productId}")
	public ApiResponse<ResponseEntity<?>>  addProductToCart(@PathVariable Integer productId){
		cartService.addProductToCart(productId);
		
		return new ApiResponse<ResponseEntity<?>>("Add product to cart success !",new ResponseEntity<>(HttpStatus.OK),0);
	}
	
	@DeleteMapping("/cart/product/{productId}")
	public ApiResponse<ResponseEntity<?>>  deleteProductToCart(@PathVariable Integer productId){
		Product product = productService.getProductById(productId);
		if(product == null) {
			throw new NotFoundProductException("Can not found product with id : " + productId);
		}	
		cartService.deleteProductToCart(productId);
		
		return new ApiResponse<ResponseEntity<?>>("Delete product to cart success !",new ResponseEntity<>(HttpStatus.OK),0);
	}

}
