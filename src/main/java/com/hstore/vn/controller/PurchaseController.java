package com.hstore.vn.controller;

import java.math.BigDecimal;
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

import com.hstore.vn.SetupDataLoader;
import com.hstore.vn.dao.PurchaseStatusDao;
import com.hstore.vn.entity.Product;
import com.hstore.vn.entity.Purchase;
import com.hstore.vn.payload.converter.PurchaseConvert;
import com.hstore.vn.payload.request.PurchaseRequest;
import com.hstore.vn.payload.request.PurchaseRequestUpdate;
import com.hstore.vn.payload.response.ApiResponse;
import com.hstore.vn.payload.response.PurchaseResponse;
import com.hstore.vn.payload.response.TotalPriceResponse;
import com.hstore.vn.service.PurchaseService;

@RestController
@RequestMapping("/api/v1")
public class PurchaseController {
	
	@Autowired
	public PurchaseService purchaseService;
	
	@Autowired
	public PurchaseConvert purchaseConvert;
	
	@Autowired
	public PurchaseStatusDao purchaseStatusDao;
	
	@GetMapping("/purchase/{id}")
	public ApiResponse<ResponseEntity<PurchaseResponse>> getPurchaseById(@PathVariable Integer id){
		Purchase purchase = purchaseService.getPurchaseById(id);
		
		PurchaseResponse purchaseResponse = purchaseConvert.purchaseConvertToPurchaseResponse(purchase);
		
		return new ApiResponse<ResponseEntity<PurchaseResponse>>(
				"Get purchase with id : " + id + " success!",
				new ResponseEntity<PurchaseResponse>(purchaseResponse,HttpStatus.OK)
				,0
			);
	}
	
	@GetMapping("/purchase-by-user")
	public ApiResponse<ResponseEntity<List<PurchaseResponse>>> getPurchasesByUser(){
		List<Purchase> purchases = purchaseService.getPurchaseByUserAuthenticated();
		
		List<PurchaseResponse> purchaseResponses = purchaseConvert.purchasesConvertToPurchasesResponse(purchases);
		
		return new ApiResponse<ResponseEntity<List<PurchaseResponse>>>(
				"Get purchase with user success!",
				new ResponseEntity<List<PurchaseResponse>>(purchaseResponses,HttpStatus.OK)
				,0
			);
	}
	
	@GetMapping("/not-complete-purchase")
	public ApiResponse<ResponseEntity<List<PurchaseResponse>>> getNotCompletePurchases(){
		Integer completePurchaseStatusId =
				purchaseStatusDao.getPurchaseStatusByName(SetupDataLoader.COMPLETED).getId();
		List<Purchase> purchases = purchaseService.getNotCompletePurchaseBy(completePurchaseStatusId);
		
		List<PurchaseResponse> purchaseResponses = purchaseConvert.purchasesConvertToPurchasesResponse(purchases);
		
		return new ApiResponse<ResponseEntity<List<PurchaseResponse>>>(
				"Get not complete purchases success!",
				new ResponseEntity<List<PurchaseResponse>>(purchaseResponses,HttpStatus.OK)
				,0
			);
	}
	
	@GetMapping("/purchase/{id}/total-price")
	public ApiResponse<ResponseEntity<TotalPriceResponse>> getTotalPrice(@PathVariable Integer id){
		Purchase purchaseDto =purchaseService.getPurchaseById(id);
		BigDecimal totalPrice = BigDecimal.valueOf(purchaseService.getTotalsMoneyByPurchase(purchaseDto));
		
		return new ApiResponse<ResponseEntity<TotalPriceResponse>>(
				"Get total price in purchase with id : " + id + " success!",
				new ResponseEntity<TotalPriceResponse>(new TotalPriceResponse(totalPrice),HttpStatus.OK)
				,0
			);
	}
	
	
	
	@PostMapping("/purchase")
	public ApiResponse<ResponseEntity<PurchaseResponse>> createPurchase(@RequestBody PurchaseRequest purchaseRequest){
		Purchase purchase = new Purchase();

		purchase.setProducts(purchaseConvert.purchaseRequestToListProduct(purchaseRequest));

		purchase.setAddress(purchaseRequest.getAddress());
		Purchase purchaseSaved = purchaseService.savePurchase(purchase);
		
		PurchaseResponse purchaseResponse = purchaseConvert.purchaseConvertToPurchaseResponse(purchaseSaved);
		
		return new ApiResponse<ResponseEntity<PurchaseResponse>>(
				"Create purchase success!",
				new ResponseEntity<PurchaseResponse>(purchaseResponse,HttpStatus.OK)
				,0
			);
	}
	
	@PostMapping("/purchase/{purchaseId}/upstatus-to-one-stage")
	public ApiResponse<ResponseEntity<PurchaseResponse>> updatePurchaseStatusUpToOneStageByPurchaseId(
			@PathVariable Integer purchaseId){
		Purchase purchase = purchaseService.updateStatusPurchaseByPurchaseIdUpToOneStage(purchaseId);
		PurchaseResponse purchaseResponse = purchaseConvert.purchaseConvertToPurchaseResponse(purchase);
		
		return new ApiResponse<ResponseEntity<PurchaseResponse>>(
				"Update purchase status up to one stage success!",
				new ResponseEntity<PurchaseResponse>(purchaseResponse,HttpStatus.OK)
				,0
			);
	}
	
	@PutMapping("/purchase")
	public ApiResponse<ResponseEntity<PurchaseResponse>> updatePurchase(@RequestBody PurchaseRequestUpdate purchaseRequestUpdate){
		Purchase purchase = purchaseService.getPurchaseById(purchaseRequestUpdate.getId());
		List<Product> products = purchaseConvert.purchaseRequestUpdateToListProduct(purchaseRequestUpdate);
		

		purchase.setProducts(products);

		PurchaseResponse purchaseResponse = purchaseConvert.purchaseConvertToPurchaseResponse(purchase);
		
		purchaseService.updatePurchase(purchase);
		
		return new ApiResponse<ResponseEntity<PurchaseResponse>>(
				"Update purchase success!",
				new ResponseEntity<PurchaseResponse>(purchaseResponse,HttpStatus.OK)
				,0
			);
	}
	
	@DeleteMapping("/purchase/{id}")
	public ApiResponse<ResponseEntity<?>> deletePurchase(@PathVariable Integer id){
		purchaseService.deletePurchase(id);
		
		return new ApiResponse<ResponseEntity<?>>("Delete purchase with id " + id + " success !",
				new ResponseEntity<>(HttpStatus.OK),0);
	}
}
