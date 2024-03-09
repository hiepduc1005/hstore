package com.hstore.vn.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hstore.vn.dao.RoleDao;
import com.hstore.vn.entity.Privilege;
import com.hstore.vn.entity.Role;
import com.hstore.vn.exception.privilege.PrivilegeNotFoundException;
import com.hstore.vn.exception.role.RoleNotFoundException;

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
//	    
		return roleDto;
	}

	@Override
	public List<Privilege> getPrivilegeByRole(Role role) {
		if(role == null) {
			throw new RoleNotFoundException("can not found role");
		}
		
		TypedQuery<Privilege> query =  entityManager.createQuery(
				"SELECT rp.privilege FROM rolesPrivileges rp WHERE rp.role = :role " , Privilege.class);
		
		query.setParameter("role", role);
		
		List<Privilege> privileges = query.getResultList();
		
		return privileges;
	}

	

}
