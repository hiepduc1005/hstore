package com.hstore.vn.payload.converter;

import org.springframework.stereotype.Service;

import com.hstore.vn.entity.PurchaseStatus;
import com.hstore.vn.entity.impl.DefaultPurchaseStatus;
import com.hstore.vn.payload.PurchaseStatusDto;
import com.hstore.vn.payload.response.PurchaseStatusResponse;


@Service
public class PurchaseStatusConvert {
	
	public PurchaseStatus convertPurchaseStatusDtoToPurchaseStatus(PurchaseStatusDto orderStatusDto) {
		if(orderStatusDto == null) {
			return null;
		}
		
		PurchaseStatus orderStatus = new DefaultPurchaseStatus();
		
		orderStatus.setOderStatusId(orderStatusDto.getId());
		orderStatus.setStatusName(orderStatusDto.getStatusName());
		
		return orderStatus;
	}
	
	public PurchaseStatusDto convertPurchaseStatusToPurchaseStatusDto(PurchaseStatus orderStatus) {
		if(orderStatus == null) {
			return null;
		}
		
		PurchaseStatusDto orderStatusDto = new PurchaseStatusDto();
		
		orderStatusDto.setId(orderStatus.getPurchaseStatusId());
		orderStatusDto.setStatusName(orderStatus.getStatusName());
		
		return orderStatusDto;
	}
	public PurchaseStatusResponse purchaseStatusConvertToPurchaseStatusResponse(PurchaseStatus purchaseStatus) {
		PurchaseStatusResponse purchaseStatusResponse = 
				new PurchaseStatusResponse(
						purchaseStatus.getPurchaseStatusId(),
						purchaseStatus.getStatusName()
						);
		return purchaseStatusResponse;
	}
}
