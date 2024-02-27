package com.hstore.vn.entity.impl;

import java.math.BigDecimal;

import com.hstore.vn.entity.Category;
import com.hstore.vn.entity.Product;


public class DefaultProduct implements Product{
	
	public Integer id;
	public String name;
	public Category category;
	public BigDecimal price;
	public String description;
	public String imgName;
	public String guid;

	@Override
	public Integer getProductId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public void setProductId(Integer id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	@Override
	public String getProductName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public void setProductName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}

	public DefaultProduct(String name, Category category, BigDecimal price, String description, String imgName) {
		this.name = name;
		this.category = category;
		this.price = price;
		this.description = description;
		this.imgName = imgName;
	}
	

	public DefaultProduct() {
		
	}

	@Override
	public Category getCategory() {
		// TODO Auto-generated method stub
		return this.category;
	}

	@Override
	public void setCategory(Category category) {
		// TODO Auto-generated method stub
		this.category = category;
	}

	@Override
	public BigDecimal getPrice() {
		// TODO Auto-generated method stub
		return this.price;
	}

	@Override
	public void setPrice(BigDecimal price) {
		// TODO Auto-generated method stub
		this.price = price;
	}

	@Override
	public String getProductDescription() {
		// TODO Auto-generated method stub
		return this.description;
	}

	@Override
	public void setProductDescription(String description) {
		// TODO Auto-generated method stub
		this.description = description;
	}

	@Override
	public String getImg() {
		// TODO Auto-generated method stub
		return this.imgName;
	}

	@Override
	public void setImg(String img) {
		// TODO Auto-generated method stub
		this.imgName = img;
	}

	@Override
	public String getProductGUID() {
		// TODO Auto-generated method stub
		return this.guid;
	}

	@Override
	public void setProductGUID(String guid) {
		// TODO Auto-generated method stub
		this.guid = guid;
	}

}
