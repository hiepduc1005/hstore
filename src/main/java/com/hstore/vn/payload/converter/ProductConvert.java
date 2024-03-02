package com.hstore.vn.payload.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hstore.vn.dao.ProductDao;
import com.hstore.vn.entity.Category;
import com.hstore.vn.entity.Product;
import com.hstore.vn.entity.impl.DefaultProduct;
import com.hstore.vn.payload.ProductDto;
import com.hstore.vn.payload.request.ProductRequest;
import com.hstore.vn.payload.request.ProductRequestUpdate;
import com.hstore.vn.payload.response.ProductResponse;
import com.hstore.vn.service.CategoryService;



@Service
public class ProductConvert {
	
	@Autowired
	public CategoryConvert categoryConvert;
	
	@Autowired
	public CategoryService categoryService;
	
	@Autowired
	public ProductDao productDao;
	
	public Product productDtoConvertToProduct(ProductDto productDto) {	
		if(productDto == null) {
			return null;
		}
		
		Product product = new DefaultProduct();

		product.setCategory(categoryConvert.categoryDtoConvertToCategory(productDto.getCategory()));	
		product.setImg(productDto.getImgName());
		product.setPrice(productDto.getPrice());
		product.setProductDescription(productDto.getDescription());
		product.setProductGUID(productDto.getGuid());
		product.setProductId(productDto.getId());
		product.setProductName(productDto.getName());
		
		return product;
	}
	
	public ProductDto productConvertToProductDto(Product product) {
		if(product == null) {
			return null;
		}
		
		ProductDto productDto = new ProductDto();
		
		  productDto.setCategory(categoryConvert.categoryConvertToCategoryDto(product.getCategory()));
		  productDto.setImgName(product.getImg());
		  productDto.setPrice(product.getPrice());
		  productDto.setDescription(product.getProductDescription());
		  productDto.setGuid(product.getProductGUID());
		  productDto.setId(product.getProductId());
		  productDto.setName(product.getProductName());
	
		
		
		return productDto;
	}
	
	public List<Product> productsDtoConvertToProducts(List<ProductDto> productDtos){
		if(productDtos == null) {
			return null;
		}
		
		List<Product> products = new ArrayList<Product>();
		
		for(ProductDto productDto : productDtos) {
			products.add(productDtoConvertToProduct(productDto));
		}
		
		return products;
	}
	
	public List<ProductDto> productsConvertToProductsDto(List<Product> products){
		if(products == null) {
			return null;
		}
		
		List<ProductDto> productsDto = new ArrayList<ProductDto>();
		
		for(Product product : products) {
			productsDto.add(productConvertToProductDto(product));
		}
		
		return productsDto;
	}
	
	public Product productRequestConvertToProduct(ProductRequest productRequest) {
		Category category = categoryService.getCategoryByName(productRequest.getCategory().getName());
		
		Product product = new DefaultProduct();
		product.setProductName(productRequest.getName());
		product.setProductDescription(productRequest.getDescription());
		product.setImg(productRequest.getImgName());
		product.setPrice(productRequest.getPrice());
		product.setCategory(category);
		
		
		return product;
	}
	
	public Product productRequestUpdateConvertToProduct(ProductRequestUpdate productRequestUpdate) {
		Category category = categoryService.getCategoryByName(productRequestUpdate.getCategory().getName());
		
		Product product = productDtoConvertToProduct(productDao.getProductById(productRequestUpdate.getId()));
		product.setProductName(productRequestUpdate.getName());
		product.setProductDescription(productRequestUpdate.getDescription());
		product.setImg(productRequestUpdate.getImgName());
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
						product.getProductId(),
						product.getProductName(),
						categoryConvert.categoryConvertToCategoryResponse( product.getCategory()),
						product.getPrice(),
						product.getProductDescription(),
						product.getImg(),
						product.getProductGUID()
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
