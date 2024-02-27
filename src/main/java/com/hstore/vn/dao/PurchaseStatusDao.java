package com.hstore.vn.dao;

import com.hstore.vn.payload.PurchaseStatusDto;

public interface PurchaseStatusDao {
	
	PurchaseStatusDto getPurchaseStatusById(Integer id);
	
	PurchaseStatusDto getPurchaseStatusByName(String statusName);
	
	void savePurchaseStatus(PurchaseStatusDto purchaseStatusDto);

}
