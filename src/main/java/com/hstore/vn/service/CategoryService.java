package com.hstore.vn.service;

import com.hstore.vn.entity.Category;

public interface CategoryService {
	
	Category getCategoryByName(String name);
	
	Category getCategoryById(Integer id);

}
