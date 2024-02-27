package com.hstore.vn.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hstore.vn.dao.PurchaseDao;
import com.hstore.vn.payload.PurchaseDto;

import jakarta.persistence.EntityManager;
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
		PurchaseDto PurchaseDto = entityManager.find(PurchaseDto.class, id);
		return PurchaseDto;
	}

	@Transactional
	@Override
	public List<PurchaseDto> getPurchasesByUserId(Integer userId) {
		TypedQuery<PurchaseDto> typedQuery = entityManager.createQuery("SELECT o FROM purchase o"
				+ " WHERE o.user.id = :userId ",PurchaseDto.class);
		
		typedQuery.setParameter("userId", userId);
		List<PurchaseDto> purchaseDtos = typedQuery.getResultList();
		return purchaseDtos;
	}

	@Transactional
	@Override
	public List<PurchaseDto> getAllPurchases() {
		TypedQuery<PurchaseDto> typedQuery = entityManager.createQuery("SELECT o FROM purchase o",PurchaseDto.class);
		List<PurchaseDto> purchaseDtos = typedQuery.getResultList();
		return purchaseDtos;
	}

	@Transactional
	@Override
	public List<PurchaseDto> getNotCompletedPurchases(Integer completedPurchaseStatusId) {
		TypedQuery<PurchaseDto> typedQuery = entityManager.createQuery("SELECT o FROM purchase o"
				+ " WHERE o.purchaseStatus.id != :completedPurchaseStatusId ",PurchaseDto.class);
		
		typedQuery.setParameter("completedPurchaseStatusId", completedPurchaseStatusId);
		List<PurchaseDto> purchaseDtos = typedQuery.getResultList();
		return purchaseDtos;
	}

}
