package com.hstore.vn.entity.impl;

import java.math.BigDecimal;
import java.util.List;


import com.hstore.vn.entity.Cart;
import com.hstore.vn.entity.Role;
import com.hstore.vn.entity.User;


public class DefaultUser implements User {
	
	public Integer id;
	public String firstName;
	public String lastName;
	public String email;
	public String phoneNum;
	public String password;
	public List<Role> roles;
	public Cart cart;
	public BigDecimal money;
	public String creditNum;
	public String partnerCode;
	public User reffererUser;
	public Boolean enabled;
	 private Boolean locked;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	@Override
	public String getFirstName() {
		// TODO Auto-generated method stub
		return this.firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		// TODO Auto-generated method stub
		this.firstName = firstName;
	}

	@Override
	public String getLastName() {
		// TODO Auto-generated method stub
		return this.lastName;
	}

	@Override
	public void setLastName(String lastName) {
		// TODO Auto-generated method stub
		this.lastName = lastName;
	}

	@Override
	public String getPhoneNum() {
		// TODO Auto-generated method stub
		return this.phoneNum;
	}

	@Override
	public void setPhoneNum(String phoneNum) {
		// TODO Auto-generated method stub
		this.phoneNum = phoneNum;
	}

	@Override
	public void setEmail(String email) {
		// TODO Auto-generated method stub
		this.email = email;
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public void setPassword(String password) {
		// TODO Auto-generated method stub
		this.password = password;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
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

	@Override
	public BigDecimal getMoney() {
		// TODO Auto-generated method stub
		return this.money;
	}

	@Override
	public void setMoney(BigDecimal money) {
		// TODO Auto-generated method stub
		this.money = money;
	}

	@Override
	public String getCreditNum() {
		// TODO Auto-generated method stub
		return this.creditNum;
	}

	@Override
	public void setCrediNum(String creditNum) {
		// TODO Auto-generated method stub
		this.creditNum = creditNum;
	}

	@Override
	public String getPartnerCode() {
		// TODO Auto-generated method stub
		return this.partnerCode;
	}

	@Override
	public void setPartnerCode(String partnerCode) {
		// TODO Auto-generated method stub
		this.partnerCode = partnerCode;
	}

	@Override
	public User getReffererUser() {
		// TODO Auto-generated method stub
		return this.reffererUser;
	}

	@Override
	public void setReffererUser(User user) {
		// TODO Auto-generated method stub
		this.reffererUser = user;
	}

	@Override
	public void setCart(Cart cart) {
		// TODO Auto-generated method stub
		this.cart = cart;
	}

	@Override
	public Cart getCart() {
		// TODO Auto-generated method stub
		return this.cart;
	}


	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public void setCreditNum(String creditNum) {
		this.creditNum = creditNum;
	}

	@Override
	public void setIsEnable(Boolean enabled) {
		this.enabled = enabled;
		
	}

	@Override
	public Boolean getEnable() {
		// TODO Auto-generated method stub
		return this.enabled;
	}
	
	
	 public boolean isCredentialsNonExpired() {
	        return true;
	    }
	 
	 public DefaultUser(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		
	}
	 
	 public DefaultUser() {
		
		}

	public boolean isAccountNonExpired() {
	        return true;
	    }
	
	

	

}
