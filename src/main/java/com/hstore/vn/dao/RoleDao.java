package com.hstore.vn.dao;

import com.hstore.vn.payload.RoleDto;

public interface RoleDao {
	void save(RoleDto roleDto); 
	
	RoleDto getRoleById(Integer id);
	
	RoleDto getRoleByName(String name);
}
