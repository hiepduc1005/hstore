package com.hstore.vn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.hstore.vn.dao.CategoryDao;
import com.hstore.vn.entity.Category;
import com.hstore.vn.service.CategoryService;


@Service
public class DefaultCategoryService implements CategoryService{
	
	@Autowired
	public CategoryDao categoryDao;
	
	@Override
	public Category getCategoryByName(String name) {
		// TODO Auto-generated method stub
		return 
				categoryDao.getCategoryByName(name);
	}

	@Override
	public Category getCategoryById(Integer id) {
		// TODO Auto-generated method stub
		return 
				categoryDao.getCategoryById(id);
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryDao.getAllCategories();
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void createCategory(Category category) {
		categoryDao.createCategory(category);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void deleteCategory(Integer id) {
		categoryDao.deleteCategory(id);
	}

}
