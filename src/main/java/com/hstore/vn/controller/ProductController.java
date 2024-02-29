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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hstore.vn.entity.Product;
import com.hstore.vn.exception.product.NotFoundProductException;
import com.hstore.vn.payload.converter.ProductConvert;
import com.hstore.vn.payload.request.ProductRequest;
import com.hstore.vn.payload.response.ApiResponse;
import com.hstore.vn.service.impl.DefaultProductService;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {
	
	@Autowired
	public DefaultProductService productService;
	
	@Autowired
	public ProductConvert productConvert;
	
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
	
	@GetMapping("/category/{categoryId}")
	public ApiResponse<ResponseEntity<List<Product>>> getProductsByCategoryWithPageAndLimit(
			@PathVariable Integer categoryId,
			@RequestParam(defaultValue = "1" , required = false) Integer pageNumber ,
			@RequestParam(defaultValue = "10" , required = false) Integer limitProduct)
	{
		
		List<Product> products = productService.getProductsByCategoryForPageWithLimit(categoryId, pageNumber, limitProduct);
		
		return new ApiResponse<ResponseEntity<List<Product>>>(
				"Get product with category id : " + categoryId + " and page = " + pageNumber + " , limit = " + limitProduct + " success !",
				new ResponseEntity<List<Product>>(products , HttpStatus.OK),0);
	}
	
	@GetMapping
	public ApiResponse<ResponseEntity<List<Product>>> getProductsBySearchWithPageAndLimit(
			@RequestParam(required = true,defaultValue = "") String searchQuery ,
			@RequestParam(required = false,defaultValue = "1") Integer pageNumber ,
			@RequestParam(required = false , defaultValue = "10") Integer limit
			)
	{
		List<Product> products = productService.getProductsLikeNameForPageWithLimit(searchQuery, pageNumber, limit);
		
		return new ApiResponse<ResponseEntity<List<Product>>>(
				"Get product with search query : " + searchQuery + " and page = " + pageNumber + " , limit = " + limit + " success !",
				new ResponseEntity<List<Product>>(products , HttpStatus.OK),0);
	}
	
	@PostMapping
	public ApiResponse<ResponseEntity<Product>> postProduct(
			@RequestBody ProductRequest productRequest ){
		
		Product product = productConvert.productRequestConvertToProduct(productRequest);
		
		productService.saveProduct(product);
	
		ResponseEntity<Product> re = new ResponseEntity<Product>(product,HttpStatus.ACCEPTED);
		
		ApiResponse<ResponseEntity<Product>> apiResponse =
				new ApiResponse<ResponseEntity<Product>>("Create product successful", re, 0);
		
		return apiResponse;
		
	}
	
	@PutMapping
	public ApiResponse<ResponseEntity<Product>> putProduct(
			@RequestBody ProductRequest productRequest ){
		
		Product product = productConvert.productRequestConvertToProduct(productRequest);
		
		
		productService.updateProduct(product);
			
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
