package com.hstore.vn.dao;

import java.util.List;

import com.hstore.vn.payload.ProductDto;


public interface ProductDao {
	
	void saveProduct(ProductDto product);
	
	void update(ProductDto productDto);
	
	void deleteProduct(String uuid);
	
	List<ProductDto> getAllProducts();
	
	ProductDto getProductByGuid(String guid);
	
	List<ProductDto> getProductsLikeName(String query);
	
	List<ProductDto> getProductByCategoryName(String name);
	
	List<ProductDto> getProductByCategoryId(Integer id);
	
	List<ProductDto> getProductsByCategoryIdWithPaginationLimit(Integer categoryId, Integer page , Integer paginationLimit);
	
	List<ProductDto> getProductLikeNameWithPaginationLimit(String query, Integer page, Integer paginationLimit);
	
	List<ProductDto> getAllProductWithPaginationLimit(Integer page, Integer paginationLimit);
 	
	Integer getProductCountBySearch(String query);
	
	Integer getProductCountByCategoryId(Integer id);
	
	Integer getProductCountByCategoryName(String categoryName);
	
	ProductDto getProductById(Integer id);
	
	ProductDto getProductByName(String name);

}
