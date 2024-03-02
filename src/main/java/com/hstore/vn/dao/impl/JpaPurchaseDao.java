package com.hstore.vn.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hstore.vn.dao.PurchaseDao;
import com.hstore.vn.exception.purchase.DeletePurchaseFailure;
import com.hstore.vn.exception.purchase.PurchaseNotFoundException;
import com.hstore.vn.payload.PurchaseDto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class JpaPurchaseDao implements PurchaseDao{
	
	@Autowired
	public EntityManager entityManager;

	@Transactional
	@Override
	public void savePurchase(PurchaseDto PurchaseDto) {
		entityManager.merge(PurchaseDto);
		
	}

	@Transactional
	@Override
	public void updatePurchase(PurchaseDto PurchaseDto) {
		entityManager.merge(PurchaseDto);
		
	}

	@Transactional
	@Override
	public PurchaseDto getPurchaseById(Integer id) {
		if(id == null || id < 1) {
			throw new IllegalArgumentException("Purchase id must be type int");
		}
		PurchaseDto purchaseDto = entityManager.find(PurchaseDto.class, id);
		if(purchaseDto == null) {
			throw new PurchaseNotFoundException("Can not found purchase with id : " + id);
		}
		return purchaseDto;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<PurchaseDto> getPurchasesByUserId(Integer userId) {
		
		if(userId == null || userId < 1) {
			throw new IllegalArgumentException("User id must be type int");
		}
		
		Query query = entityManager.createNativeQuery(
				"SELECT * FROM purchase WHERE fk_user_id = :userId",PurchaseDto.class);
		
		query.setParameter("userId", userId);
		List<PurchaseDto> purchaseDtos = query.getResultList();
		
		if(purchaseDtos == null || purchaseDtos.isEmpty()) {
			throw new PurchaseNotFoundException("Can't found any purchase");
		}
		
		
		return purchaseDtos;
	}

	@Transactional
	@Override
	public List<PurchaseDto> getAllPurchases() {
		TypedQuery<PurchaseDto> typedQuery = entityManager.createQuery("SELECT o FROM purchase o",PurchaseDto.class);
		List<PurchaseDto> purchaseDtos = typedQuery.getResultList();
		
		if(purchaseDtos == null || purchaseDtos.isEmpty()) {
			throw new PurchaseNotFoundException("Can't found any purchase");
		}
		
		return purchaseDtos;
	}

	@Transactional
	@Override
	public List<PurchaseDto> getNotCompletedPurchases(Integer completedPurchaseStatusId) {
		
		if(completedPurchaseStatusId == null || completedPurchaseStatusId < 1) {
			throw new IllegalArgumentException("Purchase Status id must be type int");
		}
		
		TypedQuery<PurchaseDto> typedQuery = entityManager.createQuery("SELECT o FROM purchase o"
				+ " WHERE o.purchaseStatus.id != :completedPurchaseStatusId ",PurchaseDto.class);
		
		typedQuery.setParameter("completedPurchaseStatusId", completedPurchaseStatusId);
		List<PurchaseDto> purchaseDtos = typedQuery.getResultList();
		return purchaseDtos;
	}

	@Override
	public void deletePurchaseById(Integer purchaseId) {
		if(purchaseId == null || purchaseId < 1) {
			throw new IllegalArgumentException("Purchase id must be type int");
		}
		if(getPurchaseById(purchaseId) == null) {
			throw new PurchaseNotFoundException("Can not found purchase with id " + purchaseId);
		}
		
		Query queryDeleteFKPruchaseId = 
				entityManager.createNativeQuery("DELETE FROM purchases_products p WHERE p.purchase_id = :id ");
		queryDeleteFKPruchaseId.setParameter("id", purchaseId);
		queryDeleteFKPruchaseId.executeUpdate();
		
		Query query = entityManager.createNativeQuery("DELETE FROM purchase p WHERE p.id = :id ");
		query.setParameter("id", purchaseId);
		int rowEffect = query.executeUpdate();
		
		if(rowEffect < 1 ) {
			throw new DeletePurchaseFailure("Delete purchase id : " + purchaseId + " failed");
		}
		
		
	}

}
