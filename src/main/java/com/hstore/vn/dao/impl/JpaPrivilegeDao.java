package com.hstore.vn.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hstore.vn.dao.PrivilegeDao;
import com.hstore.vn.entity.Privilege;
import com.hstore.vn.exception.privilege.PrivilegeNotFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class JpaPrivilegeDao implements PrivilegeDao{

	@Autowired
	public EntityManager entityManager;
	
	@Transactional
	@Override
	public void save(Privilege privilegeDto) {
		entityManager.merge(privilegeDto);
		
	}

	@Transactional
	@Override
	public Privilege getPrivilegeByName(String name) {
		TypedQuery<Privilege> typedQuery = entityManager.createQuery("SELECT p FROM privilege p"
				+ " WHERE p.name = :name",Privilege.class);
		
		typedQuery.setParameter("name",name);
		Privilege privilegeDto = typedQuery.getResultList().stream().findFirst().orElse(null);

//		if(privilegeDto == null) {
//			throw new PrivilegeNotFoundException("Can not found privilege with name : " + name);
//		}
		
		return privilegeDto;
	}

	@Transactional
	@Override
	public Privilege getPrivilegeById(Integer id) {
		Privilege privilegeDto = entityManager.find(Privilege.class, id);
		
		if(privilegeDto == null) {
			throw new PrivilegeNotFoundException("Can not found privilege with name : " + id);
		}
		
		return privilegeDto;
	}

}
