package com.hstore.vn.service;

import java.util.List;

import com.hstore.vn.entity.Product;


public interface ProductService {
	
	void saveProduct(Product product);
	
	Product getProductByGuid(String guid);
	
	List<Product> getProductsByCategoryName(String categoryName);
	
	List<Product> getProductsByCategoryId(Integer id);
	
	Product getProductById(Integer id);
	
	List<Product> getProductsLikeNameForPageWithLimit(String querySearch, Integer page,Integer paginationLimit);
	
	List<Product> getProductsByCategoryForPageWithLimit(Integer categoryId, Integer page, Integer paginationLimit );
	
	Integer getNumberOfPagesBySearch(String searchQuery);
	
	Integer getNumberOfPagesByCategoryId(Integer id);
	
	List<Product> getAllProduct();
	
	void updateProduct(Product product);
	
	void deleteProduct(String uuid);
	
	public Product getProductByName(String name);
	
	List<Product> getAllProductWithPaginationLimit(Integer page , Integer limit);
 
}
