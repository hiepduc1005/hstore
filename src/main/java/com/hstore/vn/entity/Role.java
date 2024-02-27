package com.hstore.vn.entity;

import java.util.List;

public interface Role {
	Integer getId();
	void setId(Integer id);
	
	String getName();
	void setName(String name);
	
	List<Privilege> getPrivileges();
	void setPrivilege(List<Privilege> privileges);
	
	List<User> getUsers();
	void setUsers(List<User> users);
	
}
