package com.hstore.vn.entity.impl;

import java.util.List;

import com.hstore.vn.entity.Cart;
import com.hstore.vn.entity.Product;



public class DefaultCart implements Cart{
	
	public List<Product> products;
	public Integer userId;

	@Override
	public List<Product> getProducts() {
		// TODO Auto-generated method stub
		return this.products;
	}

	@Override
	public void setProducts(List<Product> products) {
		// TODO Auto-generated method stub
		this.products = products;
	}
	

	@Override
	public void setUserId(Integer userId) {
		// TODO Auto-generated method stub
		this.userId = userId;
	}

	@Override
	public Integer getUserId() {
		// TODO Auto-generated method stub
		return this.userId;
	}

}
