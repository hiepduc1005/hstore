package com.hstore.vn.dao;

import com.hstore.vn.payload.CartDto;

public interface CartDao {
	
	CartDto getCartByUserId(Integer userId);
	
	
}
