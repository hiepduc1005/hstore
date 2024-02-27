package com.hstore.vn.entity;

import java.util.List;

public interface Cart {
	
	void setUserId(Integer userId);
	Integer getUserId();
	
	List<Product> getProducts();
	void setProducts(List<Product> products);

	

}
