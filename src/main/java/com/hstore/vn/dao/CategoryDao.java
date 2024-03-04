package com.hstore.vn.dao;

import java.util.List;

import com.hstore.vn.entity.Category;


public interface CategoryDao {
	
	Category getCategoryByName(String name);
	
	Category getCategoryById(Integer id);
	
	List<Category> getAllCategories();
	
	void createCategory(Category categoryDto);
	
	void deleteCategory(Integer id);
}
