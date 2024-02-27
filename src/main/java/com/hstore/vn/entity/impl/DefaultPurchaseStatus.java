package com.hstore.vn.entity.impl;

import com.hstore.vn.entity.PurchaseStatus;

public class DefaultPurchaseStatus implements PurchaseStatus{
	
	public Integer id;
	public String statusName;

	@Override
	public Integer getPurchaseStatusId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public void setOderStatusId(Integer id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	@Override
	public String getStatusName() {
		// TODO Auto-generated method stub
		return this.statusName;
	}

	@Override
	public void setStatusName(String statusName) {
		// TODO Auto-generated method stub
		this.statusName = statusName;
	}

}
