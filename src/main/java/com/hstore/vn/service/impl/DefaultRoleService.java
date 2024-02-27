package com.hstore.vn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hstore.vn.dao.RoleDao;
import com.hstore.vn.entity.Role;
import com.hstore.vn.payload.converter.RoleConvert;
import com.hstore.vn.service.RoleService;



@Service
public class DefaultRoleService implements RoleService{
	
	@Autowired
	public RoleConvert roleConvert;
	
	@Autowired
	public RoleDao roleDao;

	@Override
	public Role getRoleByName(String name) {
		// TODO Auto-generated method stub
		return roleConvert.roleDtoConvertToRole(roleDao.getRoleByName(name));
	}

	@Override
	public Role getRoleById(Integer id) {
		// TODO Auto-generated method stub
		return roleConvert.roleDtoConvertToRole(roleDao.getRoleById(id));
	}

}
