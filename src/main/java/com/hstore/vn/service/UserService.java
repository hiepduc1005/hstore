package com.hstore.vn.service;

import java.util.List;

import com.hstore.vn.entity.User;


public interface UserService {
	
	void registerUser(User user , String refferedUserPartnerCode);
	
	void createUser(User user , String refferedUserPartnerCode);
	
	void updateUser(User user);
	
	User getUserById(Integer id);
	
	User getUserByEmail(String email);
	
	User getUserByPartnerCode(String partnerCode);
	
	List<User> getRefferedByUserId(Integer id);
	
	List<User> getAllUser();
	
	void deleteUser(Integer id);

}
