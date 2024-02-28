package com.hstore.vn.dao;

import java.util.List;

import com.hstore.vn.payload.UserDto;


public interface UserDao {
	
	UserDto getUserById(Integer id);
	
	boolean saveUser(UserDto user);
	
	void updateUser(UserDto user);
	
	UserDto getUserByEmail(String email);
	
	List<UserDto> getAllUsers();
	
	UserDto getUserByPartnerCode(String partnerCode);
	
	List<UserDto> getRefferedByUserId(Integer id);
	
	void deleteUser(Integer id);
}
