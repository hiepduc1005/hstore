package com.hstore.vn.entity.impl;

import java.util.List;

import com.hstore.vn.entity.Privilege;
import com.hstore.vn.entity.Role;



public class DefaultPrivilege implements Privilege{
	
	public Integer id;
	public String name;
	public List<Role> roles;
	

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
	public List<Role> getRoles() {
		// TODO Auto-generated method stub
		return this.roles;
	}

	@Override
	public void setRoles(List<Role> roles) {
		// TODO Auto-generated method stub
		this.roles = roles;
	}

}
