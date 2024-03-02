package com.hstore.vn.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hstore.vn.dao.PurchaseStatusDao;
import com.hstore.vn.exception.purchasestatus.PurchaseStatusNotFoundException;
import com.hstore.vn.payload.PurchaseStatusDto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class JpaPurchaseStatusDao implements PurchaseStatusDao{
	
	@Autowired
	public EntityManager entityManager;

	@Transactional
	@Override
	public PurchaseStatusDto getPurchaseStatusById(Integer id) {
		if(id == null || id < 1) {
			throw new IllegalArgumentException("Purchase id must be type int");
		}
		PurchaseStatusDto purchaseStatusDto = entityManager.find(PurchaseStatusDto.class, id);
		if(purchaseStatusDto == null) {
			throw new PurchaseStatusNotFoundException("Can not found status with id : " + id );
		}
		return purchaseStatusDto;
	}

	@Transactional
	@Override
	public PurchaseStatusDto getPurchaseStatusByName(String statusName) {
		TypedQuery<PurchaseStatusDto> typedQuery = entityManager.createQuery(
				"SELECT o FROM purchase_status o WHERE o.statusName = :statusName ",PurchaseStatusDto.class);
		
		typedQuery.setParameter("statusName", statusName);
		PurchaseStatusDto purchaseStatusDto = typedQuery.getResultList().stream().findFirst().orElse(null);
		if(purchaseStatusDto == null) {
			throw new PurchaseStatusNotFoundException("Can not found status with name : " + statusName);
		}
		return purchaseStatusDto;
	}

	@Transactional
	@Override
	public void savePurchaseStatus(PurchaseStatusDto purchaseStatusDto) {
		entityManager.merge(purchaseStatusDto);
	}

}
