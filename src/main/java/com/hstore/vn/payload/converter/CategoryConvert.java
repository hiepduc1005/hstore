package com.hstore.vn.payload.converter;

import org.springframework.stereotype.Service;

import com.hstore.vn.entity.Category;
import com.hstore.vn.entity.impl.DefaultCategory;
import com.hstore.vn.payload.CategoryDto;



@Service
public class CategoryConvert {
	
	public Category categoryDtoConvertToCategory(CategoryDto categoryDto) {
		Category category = new DefaultCategory();
		
		category.setCategoryId(categoryDto.getId());
		category.setCategoryName(categoryDto.getName());
		
		return category;
	}
	
	public CategoryDto categoryConvertToCategoryDto(Category category) {
		CategoryDto categoryDto = new CategoryDto();
		
		categoryDto.setId(category.getCategoryId());
		categoryDto.setName(category.getCategoryName());
		
		return categoryDto;
	}

}
