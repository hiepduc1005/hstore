package com.hstore.vn.dao;

import java.util.List;

import com.hstore.vn.payload.PurchaseDto;


public interface PurchaseDao {
	
	void savePurchase(PurchaseDto PurchaseDto);
	
	void updatePurchase(PurchaseDto PurchaseDto);
	
	PurchaseDto getPurchaseById(Integer id);
	
	List<PurchaseDto> getPurchasesByUserId(Integer userId);
	
	List<PurchaseDto> getAllPurchases();
	
	List<PurchaseDto> getNotCompletedPurchases(Integer completedPurchaseStatusId);
	
	

}
