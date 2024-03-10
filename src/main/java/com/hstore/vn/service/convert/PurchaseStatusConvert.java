package com.hstore.vn.service.convert;

import org.springframework.stereotype.Service;

import com.hstore.vn.dto.response.PurchaseStatusResponse;
import com.hstore.vn.entity.PurchaseStatus;


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
