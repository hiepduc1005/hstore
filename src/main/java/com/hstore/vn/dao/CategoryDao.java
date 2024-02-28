package com.hstore.vn.dao;

import java.util.List;

import com.hstore.vn.payload.CategoryDto;


public interface CategoryDao {
	
	CategoryDto getCategoryByName(String name);
	
	CategoryDto getCategoryById(Integer id);
	
	List<CategoryDto> getAllCategories();
	
	void createCategory(CategoryDto categoryDto);
	
	void deleteCategory(Integer id);
}
