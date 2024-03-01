package com.hstore.vn.payload.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hstore.vn.entity.Purchase;
import com.hstore.vn.entity.impl.DefaultPurchase;
import com.hstore.vn.payload.PurchaseDto;



@Service
public class PurchaseConvert {
	
	@Autowired
	public PurchaseStatusConvert purchaseStatusConvert;
	
	@Autowired
	public ProductConvert productConvert;
	
	@Autowired
	public UserConvert userConvert;
	
	public Purchase convertPurchaseDtoToPurchase(PurchaseDto purchaseDto) {
		if(purchaseDto == null) {
			return null;
		}
		
		Purchase purchase = new DefaultPurchase();
		
		purchase.setPurchaseId(purchaseDto.getId());
		purchase.setLocalDateTime(purchaseDto.getLocalDateTime());
		purchase.setUser(userConvert.userDtoConvertToUser(purchaseDto.getUser()));
		purchase.setPurchaseStatus(purchaseStatusConvert.convertPurchaseStatusDtoToPurchaseStatus(purchaseDto.getPurchaseStatus()));
		purchase.setProductPurchase(productConvert.productsDtoConvertToProducts(purchaseDto.getProducts()));
		
		return purchase;
	}
	
	public PurchaseDto convertPurchaseToPurchaseDto(Purchase purchase) {
		if(purchase == null) {
			return null;
		}
		
		PurchaseDto purchaseDto = new PurchaseDto();
		
		purchaseDto.setId(purchase.getPurchaseId());
		purchaseDto.setLocalDateTime(purchase.getLocalDateTime());
		purchaseDto.setUser(userConvert.userConvertToUserDto(purchase.getUser()));
		purchaseDto.setPurchaseStatus(purchaseStatusConvert.convertPurchaseStatusToPurchaseStatusDto(purchase.getPurchaseStatus()));
		purchaseDto.setProducts(productConvert.productsConvertToProductsDto(purchase.getProductsPurchase()));
		
		return purchaseDto;
	}
	
	public List<Purchase> convertPurchasesDtoToPurchases(List<PurchaseDto> purchaseDtos){
		if(purchaseDtos == null) {
			return null;
		}
		
		List<Purchase> purchases = new ArrayList<Purchase>();
		
		for(PurchaseDto purchaseDto : purchaseDtos) {
			purchases.add(convertPurchaseDtoToPurchase(purchaseDto));
		}
		
		return purchases;
	}

}
