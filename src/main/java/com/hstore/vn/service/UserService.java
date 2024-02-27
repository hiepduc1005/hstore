package com.hstore.vn.service;

import java.util.List;

import com.hstore.vn.entity.User;
import com.hstore.vn.payload.UserDto;


public interface UserService {
	
	void registerUser(UserDto user , String refferedUserPartnerCode);
	
	void updateUser(User user);
	
	User getUserById(Integer id);
	
	User getUserByEmail(String email);
	
	User getUserByPartnerCode(String partnerCode);
	
	List<User> getRefferedByUserId(Integer id);
	
	List<UserDto> getAllUser();

}
