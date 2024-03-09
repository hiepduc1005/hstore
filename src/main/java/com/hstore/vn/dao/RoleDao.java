package com.hstore.vn.dao;

import java.util.List;

import com.hstore.vn.entity.Privilege;
import com.hstore.vn.entity.Role;

public interface RoleDao {
	void save(Role roleDto); 
	
	Role getRoleById(Integer id);
	
	Role getRoleByName(String name);
	
	List<Privilege> getPrivilegeByRole(Role role);
}
