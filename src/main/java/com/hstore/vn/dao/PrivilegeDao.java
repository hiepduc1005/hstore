package com.hstore.vn.dao;

import com.hstore.vn.entity.Privilege;

public interface PrivilegeDao {
	
	void save(Privilege privilegeDto);
	
	Privilege getPrivilegeByName(String name);
	
	Privilege getPrivilegeById(Integer id);
	
	

}
