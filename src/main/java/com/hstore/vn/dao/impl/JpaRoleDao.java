package com.hstore.vn.dao.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hstore.vn.dao.RoleDao;

import com.hstore.vn.entity.Role;
import com.hstore.vn.exception.privilege.PrivilegeNotFoundException;


import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class JpaRoleDao implements RoleDao{

	@Autowired
	public EntityManager entityManager;
	
	@Transactional
	@Override
	public void save(Role roleDto) {
		entityManager.merge(roleDto);
	}

	@Transactional
	@Override
	public Role getRoleById(Integer id) {
		Role roleDto = entityManager.find(Role.class, id);
		
		if(roleDto == null) {
			throw new PrivilegeNotFoundException("Can not found role with name : " + id);
		}
		
		return roleDto;
	}

	@Transactional
	@Override
	public Role getRoleByName(String name) {
		TypedQuery<Role> typedQuery = entityManager.createQuery("SELECT r FROM role r WHERE r.name = :name",Role.class);
		typedQuery.setParameter("name", name);
	    Role roleDto	= typedQuery.getResultList().stream().findFirst().orElse(null);
	    
//	    if(roleDto == null) {
//			throw new PrivilegeNotFoundException("Can not found role with name : " + name);
//		}
	    
		return roleDto;
	}



}
