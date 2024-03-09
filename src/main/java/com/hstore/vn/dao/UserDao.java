package com.hstore.vn.dao;

import java.util.List;

import com.hstore.vn.entity.User;


public interface UserDao {
	
	User getUserById(Integer id);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	User getUserByEmail(String email);
	
	List<User> getAllUsers();
	
	User getUserByPartnerCode(String partnerCode);
	
	List<User> getRefferedByUserId(Integer id);
	
	void deleteUser(Integer id);
}
