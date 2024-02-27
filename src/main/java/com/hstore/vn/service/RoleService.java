package com.hstore.vn.service;

import com.hstore.vn.entity.Role;

public interface RoleService {
	
	Role getRoleByName(String name);
	
	Role getRoleById(Integer id);

}
