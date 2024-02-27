package com.hstore.vn.payload.response;

import java.math.BigDecimal;
import java.util.List;

import com.hstore.vn.payload.CartDto;
import com.hstore.vn.payload.RoleDto;
import com.hstore.vn.payload.UserDto;


public class UserResponse {
	
	public Integer id;
	
	public String firstName;
	
	public String lastName;

	public String email;
	
	public String phoneNum;	
	
	public List<RoleDto> rolesDto;
	

	public CartDto cart;
	
	public BigDecimal money;
	
	public String creditNum;
	
	
	public String partnerCode;

	
	public UserResponse() {
		super();
	}

	public UserResponse(Integer id, String firstName, String lastName, String email, String phoneNum,
			List<RoleDto> rolesDto, CartDto cart, BigDecimal money, String creditNum, String partnerCode,
			UserDto reffererUser, Boolean locked, Boolean enabled) {
	
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNum = phoneNum;
		this.rolesDto = rolesDto;
		this.cart = cart;
		this.money = money;
		this.creditNum = creditNum;
		this.partnerCode = partnerCode;
		this.reffererUser = reffererUser;
		this.locked = locked;
		this.enabled = enabled;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public List<RoleDto> getRolesDto() {
		return rolesDto;
	}

	public void setRolesDto(List<RoleDto> rolesDto) {
		this.rolesDto = rolesDto;
	}

	public CartDto getCart() {
		return cart;
	}

	public void setCart(CartDto cart) {
		this.cart = cart;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getCreditNum() {
		return creditNum;
	}

	public void setCreditNum(String creditNum) {
		this.creditNum = creditNum;
	}

	public String getPartnerCode() {
		return partnerCode;
	}

	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}

	public UserDto getReffererUser() {
		return reffererUser;
	}

	public void setReffererUser(UserDto reffererUser) {
		this.reffererUser = reffererUser;
	}

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public UserDto reffererUser;
	
	 private Boolean locked = false;
	 
	 public Boolean enabled = true;
	
}
