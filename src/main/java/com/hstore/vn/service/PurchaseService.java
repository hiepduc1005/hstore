package com.hstore.vn.service;

import java.math.BigDecimal;
import java.util.List;

import com.hstore.vn.entity.Purchase;


public interface PurchaseService {
	
	Purchase savePurchase(Purchase Purchase);
	
	List<Purchase> getNotCompletePurchaseBy(Integer completedPurchaseStatusId);
	
	Purchase updateStatusPurchaseByPurchaseIdUpToOneStage(Integer purchaseId);
	
	Purchase getPurchaseById(Integer id);
	
	List<Purchase> getPurchaseByUserAuthenticated();
	
	void deletePurchase(Integer purchaseId);
	
	void updatePurchase(Purchase purchase);
	
	
	Double getTotalsMoneyByPurchase(Purchase purchaseDto);
	
	BigDecimal getTotalsMoneyInPurchaseWithAuthenticatedUser();
	
	List<Purchase> getPurchasesByUserId(Integer userId);

}
