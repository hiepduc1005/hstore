package com.hstore.vn.payload.converter;

import org.springframework.stereotype.Service;

import com.hstore.vn.entity.PurchaseStatus;
import com.hstore.vn.entity.impl.DefaultPurchaseStatus;
import com.hstore.vn.payload.PurchaseStatusDto;


@Service
public class PurchaseStatusConvert {
	
	public PurchaseStatus convertPurchaseStatusDtoToPurchaseStatus(PurchaseStatusDto orderStatusDto) {
		PurchaseStatus orderStatus = new DefaultPurchaseStatus();
		
		orderStatus.setOderStatusId(orderStatusDto.getId());
		orderStatus.setStatusName(orderStatusDto.getStatusName());
		
		return orderStatus;
	}
	
	public PurchaseStatusDto convertPurchaseStatusToPurchaseStatusDto(PurchaseStatus orderStatus) {
		PurchaseStatusDto orderStatusDto = new PurchaseStatusDto();
		
		orderStatusDto.setId(orderStatus.getPurchaseStatusId());
		orderStatusDto.setStatusName(orderStatus.getStatusName());
		
		return orderStatusDto;
	}
}
