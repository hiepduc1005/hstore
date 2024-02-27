package com.hstore.vn.entity;

import java.util.List;

public interface Purchase {
	
	Integer getPurchaseId();
	void setPurchaseId(Integer id);
	
	User getUser();
	void setUser(User user);
	
	List<Product> getProductsPurchase();
	void setProductPurchase(List<Product> products);
	
	PurchaseStatus getPurchaseStatus();
	void setPurchaseStatus(PurchaseStatus oderStatus);
	
	String getLocalDateTime();
	void setLocalDateTime(String localDateTime);

}
