package com.hstore.vn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hstore.vn.entity.Category;
import com.hstore.vn.payload.converter.CategoryConvert;
import com.hstore.vn.payload.request.CategoryRequest;
import com.hstore.vn.payload.response.ApiResponse;
import com.hstore.vn.payload.response.CategoryResponse;
import com.hstore.vn.service.CategoryService;

@RestController
@RequestMapping(path = "/api/v1/category")
public class CartegoryController {
	
	@Autowired
	public CategoryService categoryService;
	
	@Autowired
	public CategoryConvert categoryConvert;
	
	
	@GetMapping("/all")
	public ApiResponse<ResponseEntity<List<CategoryResponse>>> getAllCategories(){
		List<Category> categories = categoryService.getAllCategories();
		
		
		return new ApiResponse<ResponseEntity<List<CategoryResponse>>>(
				"Get all categories success !",
				new ResponseEntity<List<CategoryResponse>>(
						categoryConvert.categoriesConvertToCategoriesResponse(categories),
						HttpStatus.OK),0);
	}
	
	@GetMapping("/{id}")
	public ApiResponse<ResponseEntity<CategoryResponse>> getCategory(@PathVariable Integer id){
		Category category = categoryService.getCategoryById(id);
		CategoryResponse categoryResponse = categoryConvert.categoryConvertToCategoryResponse(category);
		return new ApiResponse<ResponseEntity<CategoryResponse>>(
				"Get category with id " + id + " success !",
				new ResponseEntity<CategoryResponse>(categoryResponse, HttpStatus.OK),0);
	}
	
	@PostMapping
	public ApiResponse<ResponseEntity<String>> postCategory(@RequestBody CategoryRequest categoryRequest){
		Category category = new Category();
		category.setName(categoryRequest.getName());
		categoryService.createCategory(category);
		
		return new ApiResponse<ResponseEntity<String>>("Create category  success!",
				new ResponseEntity<>(HttpStatus.OK),0);
	}
	
	 @DeleteMapping("/{id}")
	 public ApiResponse<ResponseEntity<String>> deleteCategory(@PathVariable Integer id){
		categoryService.deleteCategory(id);
		
		return new ApiResponse<ResponseEntity<String>>("Delete category with id " + id + " success!",
				new ResponseEntity<>(HttpStatus.OK),0);
	}

}
