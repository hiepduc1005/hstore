package com.hstore.vn.payload.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hstore.vn.entity.Product;
import com.hstore.vn.entity.impl.DefaultProduct;
import com.hstore.vn.payload.ProductDto;



@Service
public class ProductConvert {
	
	@Autowired
	public CategoryConvert categoryConvert;
	
	public Product productDtoConvertToProduct(ProductDto productDto) {
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
		List<Product> products = new ArrayList<Product>();
		
		for(ProductDto productDto : productDtos) {
			products.add(productDtoConvertToProduct(productDto));
		}
		
		return products;
	}
	
	public List<ProductDto> productsConvertToProductsDto(List<Product> products){
		List<ProductDto> productsDto = new ArrayList<ProductDto>();
		
		for(Product product : products) {
			productsDto.add(productConvertToProductDto(product));
		}
		
		return productsDto;
	}
	
	
	
}
