package com.hstore.vn.entity.impl;

import java.util.List;

import com.hstore.vn.entity.PurchaseStatus;
import com.hstore.vn.entity.Product;
import com.hstore.vn.entity.Purchase;
import com.hstore.vn.entity.User;



public class DefaultPurchase implements Purchase{
	
	public Integer id;
	public User user;
	public List<Product> products;
	public PurchaseStatus purchaseStatus;
	public String localDateTime;

	@Override
	public Integer getPurchaseId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public void setPurchaseId(Integer id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	@Override
	public User getUser() {
		// TODO Auto-generated method stub
		return this.user;
	}

	@Override
	public void setUser(User user) {
		// TODO Auto-generated method stub
		this.user = user;
	}

	@Override
	public List<Product> getProductsPurchase() {
		// TODO Auto-generated method stub
		return this.products;
	}

	@Override
	public void setProductPurchase(List<Product> products) {
		// TODO Auto-generated method stub
		this.products = products;
	}

	@Override
	public PurchaseStatus getPurchaseStatus() {
		// TODO Auto-generated method stub
		return this.purchaseStatus;
	}

	@Override
	public void setPurchaseStatus(PurchaseStatus purchaseStatus) {
		// TODO Auto-generated method stub
		this.purchaseStatus = purchaseStatus;
		
	}

	@Override
	public String getLocalDateTime() {
		// TODO Auto-generated method stub
		return this.localDateTime;
	}

	@Override
	public void setLocalDateTime(String localDateTime) {
		// TODO Auto-generated method stub
		this.localDateTime = localDateTime;
	}

}
