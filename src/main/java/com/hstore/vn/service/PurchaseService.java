package com.hstore.vn.service;

import java.util.List;

import com.hstore.vn.entity.Purchase;


public interface PurchaseService {
	
	void savePurchase(Purchase Purchase);
	
	List<Purchase> getNotCompletePurchaseBy(Integer completedPurchaseStatusId);
	
	void updateStatusPurchaseByPurchaseIdUpToOneStage(Integer purchaseId);

}
