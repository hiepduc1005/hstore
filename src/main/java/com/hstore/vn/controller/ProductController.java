package com.hstore.vn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hstore.vn.entity.Product;
import com.hstore.vn.entity.impl.DefaultProduct;
import com.hstore.vn.exception.product.NotFoundProductException;
import com.hstore.vn.payload.request.ProductRequest;
import com.hstore.vn.payload.response.ApiResponse;
import com.hstore.vn.service.impl.DefaultProductService;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {
	
	@Autowired
	public DefaultProductService productService;
	
	@GetMapping("/all")
	public ApiResponse<ResponseEntity<List<Product>>> getProducts(){
		List<Product> productDtos =  productService.getAllProduct();
		if(productDtos.isEmpty()) {
			throw new NotFoundProductException("Not found any product");
		}
		ResponseEntity<List<Product>> re = new ResponseEntity<List<Product>>(productDtos, HttpStatus.OK);
		ApiResponse<ResponseEntity<List<Product>>> apiResponse =
				new ApiResponse<ResponseEntity<List<Product>>>(
						"Get all products successful", re, 0);
		
		return apiResponse;
	}
	
	@GetMapping("/test")
	public String test(){
	
		
		return "Not secure Api";
	}
	
	@GetMapping("/{uuid}")
	public ApiResponse<ResponseEntity<Product>> getProductById(@PathVariable String uuid){
		Product product = productService.getProductByGuid(uuid);
		if(product == null) {
			throw new NotFoundProductException("Not found product with uuid : " + uuid);
		}
		
		ResponseEntity<Product> re = new ResponseEntity<Product>(product , HttpStatus.OK);
		ApiResponse<ResponseEntity<Product>> apiResponse =
				new ApiResponse<ResponseEntity<Product>>(
						"Get product successful", re, 0);
		
		return apiResponse;
		
	}
	
	@PostMapping
	public ApiResponse<ResponseEntity<Product>> postProduct(
			@RequestBody ProductRequest productRequest ){
		
		Product product = new DefaultProduct(
				productRequest.getName(),
				productRequest.getCategory(),
				productRequest.getPrice(),
				productRequest.getDescription(),
				productRequest.getImgName());
		
		
		productService.saveProduct(product);
			
		ResponseEntity<Product> re = new ResponseEntity<Product>(product,HttpStatus.ACCEPTED);
		
		ApiResponse<ResponseEntity<Product>> apiResponse =
				new ApiResponse<ResponseEntity<Product>>("Create product successful", re, 0);
		
		return apiResponse;
		
	}
	
	@PutMapping
	public ApiResponse<ResponseEntity<Product>> putProduct(
			@RequestBody ProductRequest productRequest ){
		
		Product product = new DefaultProduct(
				productRequest.getName(),
				productRequest.getCategory(),
				productRequest.getPrice(),
				productRequest.getDescription(),
				productRequest.getImgName());
		
		
		productService.saveProduct(product);
			
		ResponseEntity<Product> re = new ResponseEntity<Product>(product,HttpStatus.ACCEPTED);
		
		ApiResponse<ResponseEntity<Product>> apiResponse =
				new ApiResponse<ResponseEntity<Product>>("Create product successful", re, 0);
		
		return apiResponse;
		
	}
	
	@DeleteMapping("/{uuid}")
	public ApiResponse<ResponseEntity<String>> deleteProduct (@PathVariable String uuid){
		productService.deleteProduct(uuid);
		
		ResponseEntity<String> responseEntity =
				new ResponseEntity<String>(uuid, HttpStatus.OK);
		
		ApiResponse<ResponseEntity<String>> apiResponse = 
				new ApiResponse<ResponseEntity<String>>(
						"Delete user with uuid : " + uuid + " success!", responseEntity, 0);
		
		return apiResponse;
		
	}

}
