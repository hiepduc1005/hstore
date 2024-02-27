package com.hstore.vn.entity.impl;

import com.hstore.vn.entity.Category;

public class DefaultCategory implements Category{
	
	public Integer id;
	public String name;

	@Override
	public Integer getCategoryId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public void setCategoryId(Integer id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	@Override
	public void setCategoryName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}

	@Override
	public String getCategoryName() {
		// TODO Auto-generated method stub
		return this.name;
	}

}
