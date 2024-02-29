package com.hstore.vn.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hstore.vn.dao.ProductDao;
import com.hstore.vn.entity.Product;
import com.hstore.vn.payload.converter.ProductConvert;
import com.hstore.vn.service.ProductService;



@Service
public class DefaultProductService implements ProductService {
	
	
	@Autowired
	public ProductDao productDao;
	
	@Autowired
	public ProductConvert productConvert;
	

	@Override
	public Product getProductByGuid(String guid) {
		// TODO Auto-generated method stub
		return productConvert.productDtoConvertToProduct(productDao.getProductByGuid(guid));
	}
	
	@Override
	public void saveProduct(Product product) {
		product.setProductGUID(UUID.randomUUID().toString());
		productDao.saveProduct(productConvert.productConvertToProductDto(product));
	}

	@Override
	public List<Product> getProductsByCategoryName(String categoryName) {
		// TODO Auto-generated method stub
		return productConvert.productsDtoConvertToProducts(productDao.getProductByCategoryName(categoryName));
	}

	@Override
	public List<Product> getProductsByCategoryId(Integer id) {
		// TODO Auto-generated method stub
		return productConvert.productsDtoConvertToProducts(productDao.getProductByCategoryId(id));
	}

	@Override
	public Product getProductById(Integer id) {
		// TODO Auto-generated method stub
		return productConvert.productDtoConvertToProduct(productDao.getProductById(id));
	}

	@Override
	public List<Product> getProductsLikeNameForPageWithLimit(String querySearch, Integer page,
			Integer paginationLimit) {
		
		if(querySearch.isEmpty()) {
			return getAllProductWithPaginationLimit(page, paginationLimit);
		}
		
		return productConvert.productsDtoConvertToProducts(
				productDao.getProductLikeNameWithPaginationLimit(querySearch, page, paginationLimit));
	}

	@Override
	public List<Product> getProductsByCategoryForPageWithLimit(Integer categoryId, Integer page,
			Integer paginationLimit) {
		
		return productConvert.productsDtoConvertToProducts(
				productDao.getProductsByCategoryIdWithPaginationLimit(categoryId, page, paginationLimit));
	}

	@Override
	public Integer getNumberOfPagesBySearch(String searchQuery) {
		// TODO Auto-generated method stub
		return productDao.getProductCountBySearch(searchQuery);
	}

	@Override
	public Integer getNumberOfPagesByCategoryId(Integer id) {
		// TODO Auto-generated method stub
		return productDao.getProductCountByCategoryId(id);
	}

	@Override
	public List<Product> getAllProduct() {	
		return productConvert.productsDtoConvertToProducts(productDao.getAllProducts());
	}

	@Override
	public void updateProduct(Product product) {
		productDao.update(productConvert.productConvertToProductDto(product));
	}

	@Override
	public void deleteProduct(String uuid) {
		productDao.deleteProduct(uuid);
	}

	@Override
	public Product getProductByName(String name) {
		// TODO Auto-generated method stub
		return productConvert.productDtoConvertToProduct(productDao.getProductByName(name));
	}

	@Override
	public List<Product> getAllProductWithPaginationLimit(Integer page, Integer limit) {
		
		return productConvert.productsDtoConvertToProducts(
				productDao.getAllProductWithPaginationLimit(page, limit));
	}

}
