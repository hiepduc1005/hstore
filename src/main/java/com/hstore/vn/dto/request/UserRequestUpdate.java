package com.hstore.vn.dto.request;

import java.util.List;

public class UserRequestUpdate {
	
	public String firstName;
	public String lastName;
	public String email;
	public List<RoleRequest> roles;
	public String phoneNum;
	public String cardNum;
	
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
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
	
	public List<RoleRequest> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleRequest> roles) {
		this.roles = roles;
	}
	
	public UserRequestUpdate(String firstName, String lastName, String email, List<RoleRequest> roles, String phoneNum,
			String cardNum) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.roles = roles;
		this.phoneNum = phoneNum;
		this.cardNum = cardNum;
	}
	
	public UserRequestUpdate() {
		
	}
	

}
