package com.hstore.vn.entity.impl;

import java.util.List;

import com.hstore.vn.entity.Privilege;
import com.hstore.vn.entity.Role;
import com.hstore.vn.entity.User;


public class DefaultRole implements Role{
	
	public Integer id;
	public String name;
	public List<Privilege> privileges;
	public List<User> users;
	
	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public void setId(Integer id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}

	@Override
	public List<Privilege> getPrivileges() {
		// TODO Auto-generated method stub
		return this.privileges;
	}

	@Override
	public void setPrivilege(List<Privilege> privileges) {
		// TODO Auto-generated method stub
		this.privileges = privileges;
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return this.users;
	}

	@Override
	public void setUsers(List<User> users) {
		// TODO Auto-generated method stub
		this.users = users;
	}

}
