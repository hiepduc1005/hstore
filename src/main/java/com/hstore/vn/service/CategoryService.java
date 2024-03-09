package com.hstore.vn.service;

import java.util.List;

import com.hstore.vn.entity.Category;

public interface CategoryService {
	
	Category getCategoryByName(String name);
	
	Category getCategoryById(Integer id);
	
	List<Category> getAllCategories();
	
	void createCategory(Category category);
	
	void deleteCategory(Integer id);

}
