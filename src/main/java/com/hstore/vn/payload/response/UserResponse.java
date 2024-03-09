package com.hstore.vn.payload.response;

import java.math.BigDecimal;
import java.util.List;



public class UserResponse {
	
	public Integer id;
	
	public String firstName;
	
	public String lastName;

	public String email;
	
	public String phoneNum;	
	
	public List<RoleResponse> roles;
	

	public CartResponse cart;
	
	public BigDecimal money;
	
	public String creditNum;
	
	
	public String partnerCode;

	
	public UserResponse() {
		
	}

	public UserResponse(Integer id, String firstName, String lastName, String email, String phoneNum,
			List<RoleResponse> roles, CartResponse cart, BigDecimal money, String creditNum, String partnerCode,
			Integer reffererUserId, Boolean locked, Boolean enabled) {
	
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNum = phoneNum;
		this.roles = roles;
		this.cart = cart;
		this.money = money;
		this.creditNum = creditNum;
		this.partnerCode = partnerCode;
		this.reffererUserId = reffererUserId;
		this.locked = locked;
		this.enabled = enabled;
	}

	

	public Integer reffererUserId;
	
	 @SuppressWarnings("unused")
	private Boolean locked = false;
	 
	 public Boolean enabled = true;
	
}
