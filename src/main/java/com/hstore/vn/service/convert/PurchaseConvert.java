package com.hstore.vn.service.convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hstore.vn.dao.ProductDao;
import com.hstore.vn.dto.request.PurchaseRequest;
import com.hstore.vn.dto.request.PurchaseRequestUpdate;
import com.hstore.vn.dto.response.PurchaseResponse;
import com.hstore.vn.entity.Product;
import com.hstore.vn.entity.Purchase;


@Service
public class PurchaseConvert {
	
	@Autowired
	public PurchaseStatusConvert purchaseStatusConvert;
	
	@Autowired
	public ProductConvert productConvert;
	
	
	@Autowired
	public ProductDao productDao;
	
	
	public PurchaseResponse purchaseConvertToPurchaseResponse(Purchase purchase) {
		PurchaseResponse purchaseResponse = new PurchaseResponse(
				purchase.getId(),

			    productConvert.productsConverToProductsResponse(purchase.getProducts()),
			    purchaseStatusConvert.purchaseStatusConvertToPurchaseStatusResponse(purchase.getPurchaseStatus()),
			    purchase.getAddress(),
			    purchase.getLocalDateTime()
			    );
		
		return purchaseResponse;
	}
	
	public List<PurchaseResponse> purchasesConvertToPurchasesResponse(List<Purchase> purchases){
		List<PurchaseResponse> purchaseResponses = new ArrayList<PurchaseResponse>();
		
		for(Purchase purchase : purchases) {
			purchaseResponses.add(purchaseConvertToPurchaseResponse(purchase));
		}
		
		return purchaseResponses;
	}
	
	public List<Product> purchaseRequestToListProduct(PurchaseRequest purchaseRequest) {
		List<Product> products = new ArrayList<Product>();
		List<Integer> productsId  = purchaseRequest.getProducts();
		for(Integer integer : productsId) {
			products.add(productDao.getProductById(integer));
		}
		
		return products;
	}
	
	public List<Product> purchaseRequestUpdateToListProduct(PurchaseRequestUpdate purchaseRequestUpdate) {
		List<Product> products = new ArrayList<Product>();
		List<Integer> productsId  = purchaseRequestUpdate.getProductsId();
		for(Integer integer : productsId) {
			products.add(productDao.getProductById(integer));
		}
		
		return products;
	}

}
