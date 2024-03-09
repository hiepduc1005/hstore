package com.hstore.vn.payload.request;

import java.util.List;


public class UserRequest {
	
	public String firstName;
	public String lastName;
	public String email;
	public String password;
	public List<RoleRequest> roles;
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
	public List<RoleRequest> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleRequest> roles) {
		this.roles = roles;
	}
	public UserRequest(String firstName, String lastName, String email, String password, List<RoleRequest> roles) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}
	public UserRequest() {
		
	}
	
	

}
