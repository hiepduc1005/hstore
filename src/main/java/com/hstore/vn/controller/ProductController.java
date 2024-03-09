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
import com.hstore.vn.payload.converter.CategoryConvert;
import com.hstore.vn.payload.converter.ProductConvert;
import com.hstore.vn.payload.request.ProductRequest;
import com.hstore.vn.payload.request.ProductRequestUpdate;
import com.hstore.vn.payload.response.ApiResponse;
import com.hstore.vn.payload.response.CategoryResponse;
import com.hstore.vn.payload.response.ProductResponse;
import com.hstore.vn.service.ProductService;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

	@Autowired
	public ProductService productService;

	@Autowired
	public ProductConvert productConvert;

	@Autowired
	public CategoryConvert categoryConvert;

	@GetMapping("/all")
	public ApiResponse<ResponseEntity<List<ProductResponse>>> getProducts() {
		List<Product> products = productService.getAllProduct();

		List<ProductResponse> productResponses = productConvert.productsConverToProductsResponse(products);

		ResponseEntity<List<ProductResponse>> re = new ResponseEntity<List<ProductResponse>>(productResponses,
				HttpStatus.OK);
		ApiResponse<ResponseEntity<List<ProductResponse>>> apiResponse = new ApiResponse<ResponseEntity<List<ProductResponse>>>(
				"Get all products successful", re, 0);

		return apiResponse;
	}

	@GetMapping("/{uuid}")
	public ApiResponse<ResponseEntity<ProductResponse>> getProductByUuid(@PathVariable String uuid) {
		Product product = productService.getProductByGuid(uuid);
		if (product == null) {
			throw new NotFoundProductException("Not found product with uuid : " + uuid);
		}

		ProductResponse productResponse = productConvert.productConverToProductResponse(product);

		ResponseEntity<ProductResponse> re = new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
		ApiResponse<ResponseEntity<ProductResponse>> apiResponse = new ApiResponse<ResponseEntity<ProductResponse>>(
				"Get product successful", re, 0);

		return apiResponse;

	}

	@GetMapping("/category/{categoryId}")
	public ApiResponse<ResponseEntity<List<ProductResponse>>> getProductsByCategoryWithPageAndLimit(
			@PathVariable Integer categoryId,
			@RequestParam(defaultValue = "1", required = false) Integer pageNumber,
			@RequestParam(defaultValue = "10", required = false) Integer limitProduct) {

		List<Product> products = productService.getProductsByCategoryForPageWithLimit(categoryId, pageNumber,
				limitProduct);

		List<ProductResponse> productResponses = productConvert.productsConverToProductsResponse(products);

		return new ApiResponse<ResponseEntity<List<ProductResponse>>>(
				"Get product with category id : " + categoryId + " and page = " + pageNumber + " , limit = "
						+ limitProduct + " success !",
				new ResponseEntity<List<ProductResponse>>(productResponses, HttpStatus.OK), 0);
	}

	@GetMapping
	public ApiResponse<ResponseEntity<List<ProductResponse>>> getProductsBySearchWithPageAndLimit(
			@RequestParam(required = true, defaultValue = "") String searchQuery,
			@RequestParam(required = false, defaultValue = "1") Integer pageNumber,
			@RequestParam(required = false, defaultValue = "10") Integer limit) {
		List<Product> products = productService.getProductsLikeNameForPageWithLimit(searchQuery, pageNumber, limit);

		List<ProductResponse> productResponses = productConvert.productsConverToProductsResponse(products);

		return new ApiResponse<ResponseEntity<List<ProductResponse>>>(
				"Get product with search query : " + searchQuery + " and page = " + pageNumber + " , limit = " + limit
						+ " success !",
				new ResponseEntity<List<ProductResponse>>(productResponses, HttpStatus.OK), 0);
	}

	@PostMapping
	public ApiResponse<ResponseEntity<ProductResponse>> postProduct(
			@RequestBody ProductRequest productRequest) {

		Product product = productConvert.productRequestConvertToProduct(productRequest);

		Product newPro = productService.saveProduct(product);

		CategoryResponse categoryResponse = categoryConvert.categoryConvertToCategoryResponse(product.getCategory());

		ResponseEntity<ProductResponse> re = new ResponseEntity<ProductResponse>(
				new ProductResponse(
						newPro.getId(),
						newPro.getName(),
						categoryResponse,
						newPro.getPrice(),
						newPro.getDescription(),
						newPro.getImgName(),
						newPro.getGuid()),
				HttpStatus.ACCEPTED);

		ApiResponse<ResponseEntity<ProductResponse>> apiResponse = new ApiResponse<ResponseEntity<ProductResponse>>(
				"Create product successful", re, 0);

		return apiResponse;

	}

	@PutMapping
	public ApiResponse<ResponseEntity<ProductResponse>> putProduct(
			@RequestBody ProductRequestUpdate productRequestUpdate) {

		Product product = productConvert.productRequestUpdateConvertToProduct(productRequestUpdate);

		productService.updateProduct(product);

		ProductResponse productResponse = productConvert.productConverToProductResponse(product);

		ResponseEntity<ProductResponse> re = new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);

		ApiResponse<ResponseEntity<ProductResponse>> apiResponse = new ApiResponse<ResponseEntity<ProductResponse>>(
				"Update product with id " + product.getId() + " successful", re, 0);

		return apiResponse;

	}

	@DeleteMapping("/{uuid}")
	public ApiResponse<ResponseEntity<?>> deleteProduct(@PathVariable String uuid) {
		productService.deleteProduct(uuid);

		ResponseEntity<?> responseEntity = new ResponseEntity<>(HttpStatus.OK);

		ApiResponse<ResponseEntity<?>> apiResponse = new ApiResponse<ResponseEntity<?>>(
				"Delete product with uuid : " + uuid + " success!", responseEntity, 0);

		return apiResponse;

	}

}
