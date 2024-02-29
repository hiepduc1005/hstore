package com.hstore.vn.payload.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hstore.vn.entity.Cart;
import com.hstore.vn.entity.impl.DefaultCart;
import com.hstore.vn.payload.CartDto;


@Service
public class CartConvert {
	
	@Autowired
	public ProductConvert productConvert;
	
	public Cart cartDtoConvertToCart(CartDto cartDto) {
		Cart cart = new DefaultCart();
		
		cart.setProducts(productConvert.productsDtoConvertToProducts(cartDto.getProducts()));
		cart.setId(cartDto.getId());
		
		return cart;
	}
	
	public CartDto cartConvertToCartDto(Cart cart) {
		CartDto cartDto = new CartDto();
		
		cartDto.setProducts(productConvert.productsConvertToProductsDto(cart.getProducts()));
		cartDto.setId(cart.getId());
		
		return cartDto;
	}
	
	public List<Cart> cartsDtoConvertToCart(List<CartDto> cartDtos){
		List<Cart> carts = new ArrayList<Cart>();
		
		for(CartDto cartDto : cartDtos) {
			carts.add(cartDtoConvertToCart(cartDto));
		}
		
		return carts;
	}
	
	public List<CartDto> cartsConvertToCartDto(List<Cart> carts){
		List<CartDto> cartDtos = new ArrayList<CartDto>();
		
		for(Cart cart : carts) {
			cartDtos.add(cartConvertToCartDto(cart));
		}
		
		return cartDtos;
	}

}
