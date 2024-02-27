package com.hstore.vn.payload.request;

import java.util.List;

import com.hstore.vn.entity.Role;

public class UserRequest {
	
	public String firstName;
	public String lastName;
	public String email;
	public String password;
	public List<Role> roles;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public UserRequest(String firstName, String lastName, String email, String password, List<Role> roles) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}
	public UserRequest() {
		
	}
	
	

}
