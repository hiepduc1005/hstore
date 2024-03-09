package com.hstore.vn.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hstore.vn.dao.PurchaseStatusDao;
import com.hstore.vn.entity.PurchaseStatus;
import com.hstore.vn.exception.purchasestatus.PurchaseStatusNotFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class JpaPurchaseStatusDao implements PurchaseStatusDao{
	
	@Autowired
	public EntityManager entityManager;

	@Transactional
	@Override
	public PurchaseStatus getPurchaseStatusById(Integer id) {
		if(id == null || id < 1) {
			throw new IllegalArgumentException("Purchase id must be type int");
		}
		PurchaseStatus purchaseStatusDto = entityManager.find(PurchaseStatus.class, id);
		if(purchaseStatusDto == null) {
			throw new PurchaseStatusNotFoundException("Can not found status with id : " + id );
		}
		return purchaseStatusDto;
	}

	@Transactional
	@Override
	public PurchaseStatus getPurchaseStatusByName(String statusName) {
		TypedQuery<PurchaseStatus> typedQuery = entityManager.createQuery(
				"SELECT o FROM purchase_status o WHERE o.statusName = :statusName ",PurchaseStatus.class);
		
		typedQuery.setParameter("statusName", statusName);
		PurchaseStatus purchaseStatusDto = typedQuery.getResultList().stream().findFirst().orElse(null);
		
		if(purchaseStatusDto == null) {
			throw new PurchaseStatusNotFoundException("Can not found status with name : " + statusName);
		}
		
		return purchaseStatusDto;
	}

	@Transactional
	@Override
	public void savePurchaseStatus(PurchaseStatus purchaseStatusDto) {
		entityManager.merge(purchaseStatusDto);
	}

}
