package com.hstore.vn.dao;

import com.hstore.vn.entity.PurchaseStatus;

public interface PurchaseStatusDao {
	
	PurchaseStatus getPurchaseStatusById(Integer id);
	
	PurchaseStatus getPurchaseStatusByName(String statusName);
	
	void savePurchaseStatus(PurchaseStatus purchaseStatusDto);

}
