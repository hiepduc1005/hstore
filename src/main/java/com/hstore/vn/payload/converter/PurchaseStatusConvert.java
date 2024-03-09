package com.hstore.vn.payload.converter;

import org.springframework.stereotype.Service;

import com.hstore.vn.entity.PurchaseStatus;
import com.hstore.vn.payload.response.PurchaseStatusResponse;


@Service
public class PurchaseStatusConvert {
	
	
	public PurchaseStatusResponse purchaseStatusConvertToPurchaseStatusResponse(PurchaseStatus purchaseStatus) {
		PurchaseStatusResponse purchaseStatusResponse = 
				new PurchaseStatusResponse(
						purchaseStatus.getId(),
						purchaseStatus.getStatusName()
						);
		return purchaseStatusResponse;
	}
}
