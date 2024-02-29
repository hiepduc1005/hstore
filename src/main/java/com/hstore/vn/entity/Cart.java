package com.hstore.vn.entity;

import java.util.List;

public interface Cart {
	
	void setId(Integer id);
	Integer getId();
	
	List<Product> getProducts();
	void setProducts(List<Product> products);

	

}
