package com.hstore.vn.entity;

import java.util.List;



public interface Privilege {
	Integer getId();
	void setId(Integer id);
	
	String getName();
	void setName(String name);
	
	List<Role> getRoles();
	void setRoles(List<Role> roles);

}
