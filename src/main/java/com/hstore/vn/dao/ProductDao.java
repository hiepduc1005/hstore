package com.hstore.vn.dao;

import java.util.List;

import com.hstore.vn.entity.Product;


public interface ProductDao {
	
	Product saveProduct(Product product);
	
	void update(Product productDto);
	
	void deleteProduct(String uuid);
	
	List<Product> getAllProducts();
	
	Product getProductByGuid(String guid);
	
	List<Product> getProductsLikeName(String query);
	
	List<Product> getProductByCategoryName(String name);
	
	List<Product> getProductByCategoryId(Integer id);
	
	List<Product> getProductsByCategoryIdWithPaginationLimit(Integer categoryId, Integer page , Integer paginationLimit);
	
	List<Product> getProductLikeNameWithPaginationLimit(String query, Integer page, Integer paginationLimit);
	
	List<Product> getAllProductWithPaginationLimit(Integer page, Integer paginationLimit);
 	
	Integer getProductCountBySearch(String query);
	
	Integer getProductCountByCategoryId(Integer id);
	
	Integer getProductCountByCategoryName(String categoryName);
	
	Product getProductById(Integer id);
	
	Product getProductByName(String name);

}
