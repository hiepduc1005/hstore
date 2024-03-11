package com.hstore.vn.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.hstore.vn.dao.ProductDao;
import com.hstore.vn.entity.Product;
import com.hstore.vn.service.ProductService;



@Service
public class DefaultProductService implements ProductService {
	
	
	@Autowired
	public ProductDao productDao;
	
	


	@Override
	public Product getProductByGuid(String guid) {
		// TODO Auto-generated method stub
		return productDao.getProductByGuid(guid);
	}              
	
	@Override
	@PreAuthorize("hasAnyRole('ROLE_ADMIN' , 'ROLE_MANAGER')")
	public Product saveProduct(Product product) {
		product.setGuid(UUID.randomUUID().toString());
		return productDao.saveProduct(product);
	}

	@Override
	public List<Product> getProductsByCategoryName(String categoryName) {
		// TODO Auto-generated method stub
		return productDao.getProductByCategoryName(categoryName);
	}

	@Override
	public List<Product> getProductsByCategoryId(Integer id) {
		// TODO Auto-generated method stub
		return productDao.getProductByCategoryId(id);
	}

	@Override
	public Product getProductById(Integer id) {
		// TODO Auto-generated method stub
		return productDao.getProductById(id);
	}

	@Override
	public List<Product> getProductsLikeNameForPageWithLimit(String querySearch, Integer page,
			Integer paginationLimit) {
		
		if(querySearch.isEmpty()) {
			return getAllProductWithPaginationLimit(page, paginationLimit);
		}
		
		return 
				productDao.getProductLikeNameWithPaginationLimit(querySearch, page, paginationLimit);
	}

	@Override
	public List<Product> getProductsByCategoryForPageWithLimit(Integer categoryId, Integer page,
			Integer paginationLimit) {
		
		return
				productDao.getProductsByCategoryIdWithPaginationLimit(categoryId, page, paginationLimit);
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
		return productDao.getAllProducts();
	}

	@Override
	@PreAuthorize("hasAnyRole('ROLE_ADMIN' , 'ROLE_MANAGER')")
	public void updateProduct(Product product) {
		productDao.update(product);
	}

	@Override
	@PreAuthorize("hasAnyRole('ROLE_ADMIN' , 'ROLE_MANAGER')")
	public void deleteProduct(String uuid) {
		productDao.deleteProduct(uuid);
	}

	@Override
	public Product getProductByName(String name) {
		// TODO Auto-generated method stub
		return productDao.getProductByName(name);
	}

	@Override
	public List<Product> getAllProductWithPaginationLimit(Integer page, Integer limit) {
		
		return 
				productDao.getAllProductWithPaginationLimit(page, limit);
	}

}
