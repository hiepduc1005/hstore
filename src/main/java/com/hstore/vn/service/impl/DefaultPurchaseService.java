package com.hstore.vn.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hstore.vn.SetupDataLoader;
import com.hstore.vn.dao.PurchaseDao;
import com.hstore.vn.dao.PurchaseStatusDao;
import com.hstore.vn.dao.UserDao;
import com.hstore.vn.entity.Purchase;
import com.hstore.vn.entity.User;
import com.hstore.vn.exception.purchase.CreatePurchaseFailure;
import com.hstore.vn.payload.PurchaseDto;
import com.hstore.vn.payload.PurchaseStatusDto;
import com.hstore.vn.payload.ProductDto;
import com.hstore.vn.payload.UserDto;
import com.hstore.vn.payload.converter.PurchaseConvert;
import com.hstore.vn.payload.converter.PurchaseStatusConvert;
import com.hstore.vn.service.AffilateMarketing;
import com.hstore.vn.service.PurchaseService;
import com.hstore.vn.service.UserService;


@Service
public class DefaultPurchaseService implements PurchaseService {
	@Autowired
	public PurchaseDao purchaseDao;

	@Autowired
	public PurchaseConvert purchaseConvert;
	
	@Autowired
	public UserDao userDao;
	
	@Autowired
	public PurchaseStatusConvert purchaseStatusConvert;
	
	@Autowired
	public PurchaseStatusDao purchaseStatusDao;
	
	@Autowired
	public UserService userService;

	private String getCurrentDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDateTime dateTime = LocalDateTime.now();
		String date = dateTime.format(formatter);
		return date;
	}

	@Override
	public Purchase savePurchase(Purchase purchase) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getUserByEmail(authentication.getName());
		if(purchase.getProductsPurchase().isEmpty()) {
			throw new CreatePurchaseFailure("Can not save purchase! You need to add atleast one product");
		}
		PurchaseStatusDto purchaseStatusDto = purchaseStatusDao.getPurchaseStatusByName("RECEIVE_REQUEST");
		purchase.setLocalDateTime(getCurrentDate());
		purchase.setUser(user);
		purchase.setPurchaseStatus(purchaseStatusConvert.convertPurchaseStatusDtoToPurchaseStatus(purchaseStatusDto));
		purchaseDao.savePurchase(purchaseConvert.convertPurchaseToPurchaseDto(purchase));
		
		return purchase;
	}
	
	@Override
	public void updatePurchase(Purchase purchase) {
		purchaseDao.updatePurchase(purchaseConvert.convertPurchaseToPurchaseDto(purchase));
	}

	@Override
	public Purchase updateStatusPurchaseByPurchaseIdUpToOneStage(Integer purchaseId) {
		
		if(purchaseId == null || purchaseId < 1) {
			throw new IllegalArgumentException("Purchase id must be type int");
		}
		
		PurchaseDto purchaseDto = purchaseDao.getPurchaseById(purchaseId);
		PurchaseStatusDto currentStatus = purchaseDto.getPurchaseStatus();
		int newStatusId = currentStatus.getId() + 1;
		
        UserDto userDto = purchaseDto.getUser();
		UserDto inviteUser = userDto.getReffererUser();
		BigDecimal moneyReward = BigDecimal
				.valueOf(getTotalsMoneyByPurchase(purchaseDto) * AffilateMarketing.REWARD_AFFILATE_MARKETING);
		
        BigDecimal currentInviteUserMoney = inviteUser.getMoney();
        PurchaseStatusDto newStatus = purchaseStatusDao.getPurchaseStatusById(newStatusId);
		
		if(newStatus.statusName.equalsIgnoreCase(SetupDataLoader.COMPLETED) && inviteUser != null) {
				
	        inviteUser.setMoney(currentInviteUserMoney.add(moneyReward));
			userDto.setReffererUser(inviteUser);
			userDao.updateUser(inviteUser);
			userDao.updateUser(userDto);
			purchaseDto.setUser(userDto);
		}
		
		purchaseDto.setPurchaseStatus(newStatus);
		
		updatePurchase(purchaseConvert.convertPurchaseDtoToPurchase(purchaseDto));
		
		return purchaseConvert.convertPurchaseDtoToPurchase(purchaseDto);
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

	@Override
	public Purchase getPurchaseById(Integer id) {
		return purchaseConvert.convertPurchaseDtoToPurchase(purchaseDao.getPurchaseById(id));
	}

	@Override
	public List<Purchase> getPurchaseByUserAuthenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		Integer userId = userDao.getUserByEmail(username).getId();
		if(userId == null) {
			throw new UsernameNotFoundException("Not found user with name : " + username);
		}
		
		List<Purchase> purchases =
				purchaseConvert.convertPurchasesDtoToPurchases(
						purchaseDao.getPurchasesByUserId(userId));
		
		return purchases;
	}

	@Override
	public void deletePurchase(Integer purchaseId) {
		purchaseDao.deletePurchaseById(purchaseId);
	}

}
