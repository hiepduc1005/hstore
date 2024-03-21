package com.hstore.vn.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hstore.vn.SetupDataLoader;
import com.hstore.vn.dao.PurchaseDao;
import com.hstore.vn.dao.PurchaseStatusDao;
import com.hstore.vn.dao.UserDao;
import com.hstore.vn.entity.Cart;
import com.hstore.vn.entity.Product;
import com.hstore.vn.entity.Purchase;
import com.hstore.vn.entity.PurchaseStatus;

import com.hstore.vn.entity.User;
import com.hstore.vn.exception.purchase.CreatePurchaseFailure;
import com.hstore.vn.exception.purchasestatus.PurchaseStatusNotFoundException;
import com.hstore.vn.service.AffilateMarketing;
import com.hstore.vn.service.PurchaseService;
import com.hstore.vn.service.UserService;


@Service
public class DefaultPurchaseService implements PurchaseService {
	@Autowired
	public PurchaseDao purchaseDao;

	
	@Autowired
	public UserDao userDao;
	
	
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

		if(purchase.getProducts().isEmpty()) {
			throw new CreatePurchaseFailure("Can not save purchase! You need to add atleast one product");
		}
		PurchaseStatus purchaseStatus = purchaseStatusDao.getPurchaseStatusByName("RECEIVE_REQUEST");
		purchase.setLocalDateTime(getCurrentDate());
		purchase.setUser(user);
		purchase.setPurchaseStatus(purchaseStatus);
		purchaseDao.savePurchase(purchase);

		return purchase;
	}
	
	@Override
	public void updatePurchase(Purchase purchase) {
		purchaseDao.updatePurchase(purchase);
	}

	
	@Override
	@PreAuthorize("hasAnyRole('ROLE_ADMIN' , 'ROLE_MANAGER')")
	public Purchase updateStatusPurchaseByPurchaseIdUpToOneStage(Integer purchaseId) {

		
		if(purchaseId == null || purchaseId < 1) {
			throw new IllegalArgumentException("Purchase id must be type int");
		}
		
		Purchase purchase = purchaseDao.getPurchaseById(purchaseId);
		PurchaseStatus currentStatus = purchase.getPurchaseStatus();
		if(currentStatus.getStatusName().equalsIgnoreCase(SetupDataLoader.COMPLETED)) {
			throw new PurchaseStatusNotFoundException("Can't update purchase completed");
		}
		
		int newStatusId = currentStatus.getId() + 1;
		
        User userDto = purchase.getUser();
		Integer inviteUserId = userDto.getReffererUser();
		
		BigDecimal moneyReward = BigDecimal
				.valueOf(getTotalsMoneyByPurchase(purchase) * AffilateMarketing.REWARD_AFFILATE_MARKETING);
		
       
        PurchaseStatus newStatus = purchaseStatusDao.getPurchaseStatusById(newStatusId);
		
        if(inviteUserId != null && inviteUserId >= 1) {
        	User inviteUser = userService.getUserById(inviteUserId);
        	
        	if(newStatus.statusName.equalsIgnoreCase(SetupDataLoader.COMPLETED) && inviteUser != null) {
    		    BigDecimal currentInviteUserMoney = inviteUser.getMoney();
    	        inviteUser.setMoney(currentInviteUserMoney.add(moneyReward));
//    			userDto.setReffererUser(inviteUser.getId());
    			userDao.updateUser(inviteUser);
//    			userDao.updateUser(userDto);
    			purchase.setUser(userDto);
    		}
        	
        }
		
		
		purchase.setPurchaseStatus(newStatus);
		
		updatePurchase(purchase);
		

		return purchase;
	}

	@Override
	public Double getTotalsMoneyByPurchase(Purchase purchase) {
		List<Product> productDtos = purchase.getProducts();
		double res = 0;
		
        if(productDtos != null && !productDtos.isEmpty()) {
        	for (Product productDto : productDtos) {
			res += productDto.getPrice().doubleValue();
		}
     }
		

		return res;
	}
	
	@Override
	public BigDecimal getTotalsMoneyInPurchaseWithAuthenticatedUser() {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		UserDto userDto = userDao.getUserByEmail(authentication.getName());
//		PurchaseDto purchaseDto = purchaseDao.getPurchasesByUserId(userDto.getId());

		return null;
	}

	
	@Override
	@PreAuthorize("hasAnyRole('ROLE_ADMIN' , 'ROLE_MANAGER')")
	public List<Purchase> getNotCompletePurchaseBy(Integer completedPurchaseStatusId) {
		// TODO Auto-generated method stub
		return purchaseDao.getNotCompletedPurchases(completedPurchaseStatusId);
	}

	@Override
	@PreAuthorize("hasAnyRole('ROLE_ADMIN' , 'ROLE_MANAGER')")
	public Purchase getPurchaseById(Integer id) {
		return purchaseDao.getPurchaseById(id);
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
						purchaseDao.getPurchasesByUserId(userId);
		

		return purchases;
	}

	@Override
	@PreAuthorize("hasAnyRole('ROLE_ADMIN' , 'ROLE_MANAGER')")
	public void deletePurchase(Integer purchaseId) {
		purchaseDao.deletePurchaseById(purchaseId);
	}

	@Override
	public List<Purchase> getPurchasesByUserId(Integer userId) {
		return purchaseDao.getPurchasesByUserId(userId);
	}

	@Override
	public Purchase createPurchaseByCartUser(Integer userId) {
		User user = userDao.getUserById(userId);
		Cart cart = user.getCart();
		List<Product> products = cart.getProducts();
		
		Purchase purchase = new Purchase();
		purchase.setProducts(products);
		
		return savePurchase(purchase);
	}


}
