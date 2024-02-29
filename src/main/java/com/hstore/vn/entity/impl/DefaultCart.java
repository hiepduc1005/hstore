package com.hstore.vn.entity.impl;

import java.util.List;

import com.hstore.vn.entity.Cart;
import com.hstore.vn.entity.Product;



public class DefaultCart implements Cart{
	public Integer id;
	public List<Product> products;
	

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
	public void setId(Integer id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

}
