package com.hstore.vn.entity.impl;

import java.util.List;

import com.hstore.vn.entity.Product;
import com.hstore.vn.entity.WishList;


public class DefaultWishList implements WishList{
	
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

}
