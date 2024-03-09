package com.hstore.vn.payload.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hstore.vn.dao.CategoryDao;
import com.hstore.vn.entity.Category;
import com.hstore.vn.payload.request.CategoryRequest;
import com.hstore.vn.payload.response.CategoryResponse;



@Service
public class CategoryConvert {
	
	@Autowired
	public CategoryDao categoryDao;

	public Category categoryRequestConvertToCategory(CategoryRequest categoryRequest) {
		Category category =categoryDao.getCategoryByName(categoryRequest.getName());
		return category;
	}
	
	public CategoryResponse categoryConvertToCategoryResponse(Category category) {
		CategoryResponse categoryResponse = 
				new CategoryResponse(
						category.getId(),
						category.getName()
						);
		
		return categoryResponse;
	}
	
	public List<CategoryResponse> categoriesConvertToCategoriesResponse(List<Category> categories){
		List<CategoryResponse> categoryResponses = new ArrayList<CategoryResponse>();
		
		for(Category category : categories) {
			categoryResponses.add(categoryConvertToCategoryResponse(category));
		}
		
		return categoryResponses;
	}


}
