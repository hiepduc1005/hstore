package com.hstore.vn.payload.response;

public class PurchaseStatusResponse {
	
	public Integer statusId;
	
	public String statusName;

	public PurchaseStatusResponse(Integer statusId, String statusName) {
		this.statusId = statusId;
		this.statusName = statusName;
	}
}
