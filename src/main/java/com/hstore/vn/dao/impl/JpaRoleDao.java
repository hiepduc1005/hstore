package com.hstore.vn.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hstore.vn.dao.RoleDao;
import com.hstore.vn.payload.RoleDto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class JpaRoleDao implements RoleDao{

	@Autowired
	public EntityManager entityManager;
	
	@Transactional
	@Override
	public void save(RoleDto roleDto) {
		entityManager.merge(roleDto);
	}

	@Transactional
	@Override
	public RoleDto getRoleById(Integer id) {
		RoleDto roleDto = entityManager.find(RoleDto.class, id);
		return roleDto;
	}

	@Transactional
	@Override
	public RoleDto getRoleByName(String name) {
		TypedQuery<RoleDto> typedQuery = entityManager.createQuery("SELECT r FROM role r WHERE r.name = :name",RoleDto.class);
		typedQuery.setParameter("name", name);
	    RoleDto roleDto	= typedQuery.getResultList().stream().findFirst().orElse(null);
		return roleDto;
	}

}
