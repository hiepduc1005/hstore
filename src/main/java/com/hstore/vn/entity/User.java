package com.hstore.vn.entity;

import java.math.BigDecimal;
import java.util.List;

public interface User {
	Integer getId();
	void setId(Integer id);
	String getFirstName();
	void setFirstName(String firstName);
	String getLastName();
	void setLastName(String lastName);
	String getPhoneNum();
	void setPhoneNum(String phoneNum);
	void setEmail(String email);
	String getEmail();
	void setPassword(String password);
	String getPassword();
    List<Role> getRoles();
	void setRoles(List<Role> role);
	BigDecimal getMoney();
	void setMoney(BigDecimal money);
	String getCreditNum();
	void setCrediNum(String creditNum);
	String getPartnerCode();
	void setPartnerCode(String partnerCode);
	User getReffererUser();
	void setReffererUser(User user);
	void setIsEnable(Boolean b);
	Boolean getEnable();
	void setCart(Cart cart);
	Cart getCart();
}
