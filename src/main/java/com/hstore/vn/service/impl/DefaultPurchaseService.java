package com.hstore.vn.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hstore.vn.SetupDataLoader;
import com.hstore.vn.dao.PurchaseDao;
import com.hstore.vn.entity.Purchase;
import com.hstore.vn.payload.PurchaseDto;
import com.hstore.vn.payload.PurchaseStatusDto;
import com.hstore.vn.payload.ProductDto;
import com.hstore.vn.payload.UserDto;
import com.hstore.vn.payload.converter.PurchaseConvert;
import com.hstore.vn.service.AffilateMarketing;
import com.hstore.vn.service.PurchaseService;


@Service
public class DefaultPurchaseService implements PurchaseService {
	@Autowired
	public PurchaseDao purchaseDao;

	@Autowired
	public PurchaseConvert purchaseConvert;

	private String getCurrentDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDateTime dateTime = LocalDateTime.now();
		String date = dateTime.format(formatter);
		return date;
	}

	@Override
	public void savePurchase(Purchase purchase) {
		purchase.setLocalDateTime(getCurrentDate());
		purchaseDao.savePurchase(purchaseConvert.convertPurchaseToPurchaseDto(purchase));
	}

	@Override
	public void updateStatusPurchaseByPurchaseIdUpToOneStage(Integer purchaseId) {

		try {
			PurchaseDto purchaseDto = purchaseDao.getPurchaseById(purchaseId);
			UserDto userDto = purchaseDto.getUser();
			PurchaseStatusDto purchaseStatusDto = purchaseDto.getPurchaseStatus();
			Integer currentStatusId =purchaseStatusDto.getId();
			Integer newStatusId = currentStatusId + 1;
			BigDecimal moneyReward = BigDecimal
					.valueOf(getTotalsMoneyByPurchase(purchaseDto) * AffilateMarketing.REWARD_AFFILATE_MARKETING);
			BigDecimal currentUserMoney = userDto.getMoney();

			if (!purchaseStatusDto.statusName.equalsIgnoreCase(SetupDataLoader.COMPLETED)) {
				purchaseStatusDto.setId(newStatusId);
				purchaseDto.setPurchaseStatus(purchaseStatusDto);
			} else {
				UserDto userInvite = userDto.getReffererUser();
				userInvite.setMoney(currentUserMoney.add(moneyReward));
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

	}

	private Double getTotalsMoneyByPurchase(PurchaseDto purchaseDto) {
		List<ProductDto> productDtos = purchaseDto.getProducts();
		double res = 0;
		
        if(productDtos != null && !productDtos.isEmpty()) {
        	for (ProductDto productDto : productDtos) {
			res += productDto.getPrice().doubleValue();
		}
     }
		

		return res;
	}

	@Override
	public List<Purchase> getNotCompletePurchaseBy(Integer completedPurchaseStatusId) {
		// TODO Auto-generated method stub
		return purchaseConvert.convertPurchasesDtoToPurchases(purchaseDao.getNotCompletedPurchases(completedPurchaseStatusId));
	}

}
