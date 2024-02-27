package com.hstore.vn.service;

import java.util.List;

import com.hstore.vn.entity.User;
import com.hstore.vn.entity.impl.DefaultUser;
import com.hstore.vn.payload.UserDto;


public interface UserService {
	
	void registerUser(UserDto user , String refferedUserPartnerCode);
	
	void updateUser(DefaultUser user);
	
	User getUserById(Integer id);
	
	User getUserByEmail(String email);
	
	User getUserByPartnerCode(String partnerCode);
	
	List<User> getRefferedByUserId(Integer id);
	
	

}
