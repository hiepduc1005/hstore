package com.hstore.vn.dao.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hstore.vn.dao.CartDao;
import com.hstore.vn.payload.CartDto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class JpaCartDao implements CartDao{

	@Autowired
	public EntityManager entityManager;
	
	@Transactional
	@Override
	public CartDto getCartByUserId(Integer userId) {
		TypedQuery<CartDto> typedQuery = entityManager.createQuery(
				"SELECT c FROM cart c WHERE c.userId = :userId" , CartDto.class);
		
		typedQuery.setParameter("userId", userId);
		CartDto cartDto = typedQuery.getResultList().stream().findFirst().orElse(null);
		return cartDto;
	}


}
