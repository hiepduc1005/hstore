package com.hstore.vn.payload.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hstore.vn.dao.CategoryDao;
import com.hstore.vn.entity.Category;
import com.hstore.vn.entity.impl.DefaultCategory;
import com.hstore.vn.payload.CategoryDto;
import com.hstore.vn.payload.request.CategoryRequest;



@Service
public class CategoryConvert {
	
	@Autowired
	public CategoryDao categoryDao;
	
	public Category categoryDtoConvertToCategory(CategoryDto categoryDto) {
		if(categoryDto == null) {
			return null;
		}
		
		Category category = new DefaultCategory();
		
		category.setCategoryId(categoryDto.getId());
		category.setCategoryName(categoryDto.getName());
		
		return category;
	}
	
	public CategoryDto categoryConvertToCategoryDto(Category category) {
		if(category == null) {
			return null;
		}
		
		CategoryDto categoryDto = new CategoryDto();
		
		categoryDto.setId(category.getCategoryId());
		categoryDto.setName(category.getCategoryName());
		
		return categoryDto;
	}
	
	public List<Category> categoriesDtoConvertToCategories(List<CategoryDto> categoryDtos){
		if(categoryDtos == null) {
			return null;
		}
		
		List<Category> categories = new ArrayList<Category>();
		
		for(CategoryDto categoryDto : categoryDtos) {
			categories.add(categoryDtoConvertToCategory(categoryDto));
		}
		
		return categories;
	}
	
	public List<CategoryDto> categoriesConvertToCategoriesDto(List<Category> categories){
		if(categories == null) {
			return null;
		}
		
		List<CategoryDto> categoriesDto = new ArrayList<CategoryDto>();
		
		for(Category category : categories) {
			categoriesDto.add(categoryConvertToCategoryDto(category));
		}
		
		return categoriesDto;
	}
	
	public Category categoryRequestConvertToCategory(CategoryRequest categoryRequest) {
		CategoryDto categoryDto =categoryDao.getCategoryByName(categoryRequest.getName());
		return categoryDtoConvertToCategory(categoryDto);
	}


}
