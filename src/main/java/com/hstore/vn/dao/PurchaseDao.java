package com.hstore.vn.dao;

import java.util.List;

import com.hstore.vn.entity.Purchase;


public interface PurchaseDao {
	
	void savePurchase(Purchase PurchaseDto);
	
	void updatePurchase(Purchase PurchaseDto);
	
	Purchase getPurchaseById(Integer id);
	
	List<Purchase> getPurchasesByUserId(Integer userId);
	
	List<Purchase> getAllPurchases();
	
	List<Purchase> getNotCompletedPurchases(Integer completedPurchaseStatusId);
	
	void deletePurchaseById(Integer purchaseId);
	
	void deletePurchaseByUserId(Integer userId);

}
