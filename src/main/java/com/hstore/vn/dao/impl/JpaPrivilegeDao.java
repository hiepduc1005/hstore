package com.hstore.vn.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hstore.vn.dao.PrivilegeDao;
import com.hstore.vn.exception.privilege.PrivilegeNotFoundException;
import com.hstore.vn.payload.PrivilegeDto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class JpaPrivilegeDao implements PrivilegeDao{

	@Autowired
	public EntityManager entityManager;
	
	@Transactional
	@Override
	public void save(PrivilegeDto privilegeDto) {
		entityManager.merge(privilegeDto);
		
	}

	@Transactional
	@Override
	public PrivilegeDto getPrivilegeByName(String name) {
		TypedQuery<PrivilegeDto> typedQuery = entityManager.createQuery("SELECT p FROM privilege p"
				+ " WHERE p.name = :name",PrivilegeDto.class);
		
		typedQuery.setParameter("name",name);
		PrivilegeDto privilegeDto = typedQuery.getResultList().stream().findFirst().orElse(null);

		if(privilegeDto == null) {
			throw new PrivilegeNotFoundException("Can not found privilege with name : " + name);
		}
		
		return privilegeDto;
	}

	@Transactional
	@Override
	public PrivilegeDto getPrivilegeById(Integer id) {
		PrivilegeDto privilegeDto = entityManager.find(PrivilegeDto.class, id);
		
		if(privilegeDto == null) {
			throw new PrivilegeNotFoundException("Can not found privilege with name : " + id);
		}
		
		return privilegeDto;
	}

}
