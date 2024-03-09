package com.hstore.vn.service;

import java.util.List;

import com.hstore.vn.entity.Privilege;
import com.hstore.vn.entity.Role;

public interface RoleService {
	
	Role getRoleByName(String name);
	
	Role getRoleById(Integer id);
	
	List<Privilege> getPrivilegesByRole(Role role);

}
