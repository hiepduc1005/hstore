package com.hstore.vn.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hstore.vn.dao.PurchaseDao;
import com.hstore.vn.dao.UserDao;
import com.hstore.vn.entity.Purchase;
import com.hstore.vn.exception.purchase.PurchaseNotFoundException;
import com.hstore.vn.exception.user.UserNotFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class JpaPurchaseDao implements PurchaseDao{
	
	@Autowired
	public EntityManager entityManager;
	
	@Autowired
	public UserDao userDao;

	@Transactional
	@Override
	public void savePurchase(Purchase PurchaseDto) {
		entityManager.merge(PurchaseDto);
		
	}

	@Transactional
	@Override
	public void updatePurchase(Purchase PurchaseDto) {
		entityManager.merge(PurchaseDto);
		
	}

	@Transactional
	@Override
	public Purchase getPurchaseById(Integer id) {
		if(id == null || id < 1) {
			throw new IllegalArgumentException("Purchase id must be type int");
		}
		Purchase purchaseDto = entityManager.find(Purchase.class, id);
		if(purchaseDto == null) {
			throw new PurchaseNotFoundException("Can not found purchase with id : " + id);
		}
		return purchaseDto;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Purchase> getPurchasesByUserId(Integer userId) {
		
		if(userId == null || userId < 1) {
			throw new IllegalArgumentException("User id must be type int");
		}
		
		Query query = entityManager.createNativeQuery(
				"SELECT * FROM purchase WHERE user_id = :userId",Purchase.class);
		
		query.setParameter("userId", userId);
		List<Purchase> purchaseDtos = query.getResultList();
		
		if(purchaseDtos == null || purchaseDtos.isEmpty()) {
			throw new PurchaseNotFoundException("Can't found any purchase");
		}
		
		
		return purchaseDtos;
	}

	@Transactional
	@Override
	public List<Purchase> getAllPurchases() {
		TypedQuery<Purchase> typedQuery = entityManager.createQuery("SELECT o FROM purchase o",Purchase.class);
		List<Purchase> purchaseDtos = typedQuery.getResultList();
		
		if(purchaseDtos == null || purchaseDtos.isEmpty()) {
			throw new PurchaseNotFoundException("Can't found any purchase");
		}
		
		return purchaseDtos;
	}

	@Transactional
	@Override
	public List<Purchase> getNotCompletedPurchases(Integer completedPurchaseStatusId) {
		
		if(completedPurchaseStatusId == null || completedPurchaseStatusId < 1) {
			throw new IllegalArgumentException("Purchase Status id must be type int");
		}
		
		TypedQuery<Purchase> typedQuery = entityManager.createQuery("SELECT o FROM purchase o"
				+ " WHERE o.purchaseStatus.id != :completedPurchaseStatusId ",Purchase.class);
		
		typedQuery.setParameter("completedPurchaseStatusId", completedPurchaseStatusId);
		List<Purchase> purchaseDtos = typedQuery.getResultList();
		return purchaseDtos;
	}

	@Transactional
	@Override
	public void deletePurchaseById(Integer purchaseId) {
		if(purchaseId == null || purchaseId < 1) {
			throw new IllegalArgumentException("Purchase id must be type int");
		}
		
		Purchase purchase = getPurchaseById(purchaseId);
		
		if(purchase == null) {
			throw new PurchaseNotFoundException("Can not found purchase with id " + purchaseId);
		}
		
//		Query queryDeleteFKPruchaseId = 
//				entityManager.createNativeQuery("DELETE FROM purchases_products p WHERE p.purchase_id = :id ");
//		queryDeleteFKPruchaseId.setParameter("id", purchaseId);
//		queryDeleteFKPruchaseId.executeUpdate();
//		
//		Query query = entityManager.createNativeQuery("DELETE FROM purchase p WHERE p.id = :id ");
//		query.setParameter("id", purchaseId);
//		int rowEffect = query.executeUpdate();
//		
//		if(rowEffect < 1 ) {
//			throw new DeletePurchaseFailure("Delete purchase id : " + purchaseId + " failed");
//		}
		
//		Query queryDeleteFKPruchaseId = 
//				entityManager.createNativeQuery("DELETE FROM purchases_products p WHERE p.purchase_id = :id ");
//		queryDeleteFKPruchaseId.setParameter("id", purchaseId);
//		queryDeleteFKPruchaseId.executeUpdate();
				
		
		entityManager.remove(purchase);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public void deletePurchaseByUserId(Integer userId) {
		// TODO Auto-generated method stub
		if(userId == null || userId < 1) {
			throw new IllegalArgumentException("User id must be type int");
		}
		if(userDao.getUserById(userId).getPurchases() == null) {
			throw new UserNotFoundException("Can not found user purchase id : " + userId);
		}
		
		Query querySelectPruchaseId = entityManager.createNativeQuery("SELECT id FROM purchase WHERE user_id = :id" ,Integer.class );
		querySelectPruchaseId.setParameter("id", userId);
				
		Integer purchaseId = (Integer)querySelectPruchaseId.getResultList().stream().findFirst().orElse(null);
		Purchase purchase = getPurchaseById(purchaseId);
		
		if(purchase == null) {
			throw new PurchaseNotFoundException("Can not found purchase with id : " + purchaseId);
		}
		
		entityManager.remove(purchase);
		
//		Query queryDeleteFKPruchaseId = 
//				entityManager.createNativeQuery("DELETE FROM purchases_products p WHERE p.purchase_id = :id ");
//		queryDeleteFKPruchaseId.setParameter("id", purchaseId);
//		queryDeleteFKPruchaseId.executeUpdate();
//		
//		Query query = entityManager.createNativeQuery("DELETE FROM purchase WHERE user_id = :id ");
//		query.setParameter("id", userId);
//		query.executeUpdate();
		
//		if(rowEffect < 1 ) {
//			throw new DeletePurchaseFailure("Delete purchase by user id : " + userId + " failed");
//		}
		
	}

}
