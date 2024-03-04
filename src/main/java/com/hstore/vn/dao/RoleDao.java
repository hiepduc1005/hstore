package com.hstore.vn.dao;

import com.hstore.vn.entity.Role;

public interface RoleDao {
	void save(Role roleDto); 
	
	Role getRoleById(Integer id);
	
	Role getRoleByName(String name);
}
