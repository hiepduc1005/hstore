package com.hstore.vn.service.convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hstore.vn.dao.ProductDao;
import com.hstore.vn.dto.request.ProductRequest;
import com.hstore.vn.dto.request.ProductRequestUpdate;
import com.hstore.vn.dto.response.ProductResponse;
import com.hstore.vn.entity.Category;
import com.hstore.vn.entity.Product;
import com.hstore.vn.service.CategoryService;



@Service
public class ProductConvert {
	
	@Autowired
	public CategoryConvert categoryConvert;
	
	@Autowired
	public CategoryService categoryService;
	
	@Autowired
	public ProductDao productDao;
	
	
	public Product productRequestConvertToProduct(ProductRequest productRequest) {
		Category category = categoryService.getCategoryByName(productRequest.getCategory().getName());
		
		Product product = new Product();
		product.setName(productRequest.getName());
		product.setDescription(productRequest.getDescription());
		product.setImgName(productRequest.getImgName());
		product.setPrice(productRequest.getPrice());
		product.setCategory(category);
		
		
		return product;
	}
	
	public Product productRequestUpdateConvertToProduct(ProductRequestUpdate productRequestUpdate) {
		Category category = categoryService.getCategoryByName(productRequestUpdate.getCategory().getName());
		
		Product product =productDao.getProductById(productRequestUpdate.getId());
		product.setName(productRequestUpdate.getName());
		product.setDescription(productRequestUpdate.getDescription());
		product.setImgName(productRequestUpdate.getImgName());
		product.setPrice(productRequestUpdate.getPrice());
		product.setCategory(category);
		
		
		return product;
	}
	
	public ProductResponse productConverToProductResponse(Product product) {
		if(product == null) {
			return null;
		}
		
		ProductResponse productResponse = 
				new ProductResponse(
						product.getId(),
						product.getName(),
						categoryConvert.categoryConvertToCategoryResponse( product.getCategory()),
						product.getPrice(),
						product.getDescription(),
						product.getImgName(),
						product.getGuid()
						);
		
		return productResponse;
	}
	
	public List<ProductResponse> productsConverToProductsResponse(List<Product> products){
		List<ProductResponse> productResponses = new ArrayList<ProductResponse>();
		
		for(Product product : products) {
			productResponses.add(productConverToProductResponse(product));
		}
		
		return productResponses;
	}
	
	
}
