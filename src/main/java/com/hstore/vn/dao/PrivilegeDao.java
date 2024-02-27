package com.hstore.vn.dao;

import com.hstore.vn.payload.PrivilegeDto;

public interface PrivilegeDao {
	
	void save(PrivilegeDto privilegeDto);
	
	PrivilegeDto getPrivilegeByName(String name);
	
	PrivilegeDto getPrivilegeById(Integer id);
	
	

}
