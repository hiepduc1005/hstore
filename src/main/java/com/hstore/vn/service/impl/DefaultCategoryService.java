package com.hstore.vn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hstore.vn.dao.CategoryDao;
import com.hstore.vn.entity.Category;
import com.hstore.vn.payload.converter.CategoryConvert;
import com.hstore.vn.service.CategoryService;


@Service
public class DefaultCategoryService implements CategoryService{
	
	@Autowired
	public CategoryDao categoryDao;
	
	@Autowired
	public CategoryConvert categoryConvert;

	@Override
	public Category getCategoryByName(String name) {
		// TODO Auto-generated method stub
		return categoryConvert.categoryDtoConvertToCategory(
				categoryDao.getCategoryByName(name));
	}

	@Override
	public Category getCategoryById(Integer id) {
		// TODO Auto-generated method stub
		return categoryConvert.categoryDtoConvertToCategory(
				categoryDao.getCategoryById(id));
	}

}