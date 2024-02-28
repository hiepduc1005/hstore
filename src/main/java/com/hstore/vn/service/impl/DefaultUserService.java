package com.hstore.vn.service.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hstore.vn.SetupDataLoader;
import com.hstore.vn.dao.RoleDao;
import com.hstore.vn.dao.UserDao;
import com.hstore.vn.entity.User;
import com.hstore.vn.exception.auth.EmailAlreadyExitsException;
import com.hstore.vn.payload.UserDto;
import com.hstore.vn.payload.converter.RoleConvert;
import com.hstore.vn.payload.converter.UserConvert;
import com.hstore.vn.service.GenneratePartnerCode;
import com.hstore.vn.service.UserService;

@Service
public class DefaultUserService implements UserService{
	
	@Autowired
	public UserConvert userConvert;
	
	@Autowired
	public UserDao userDao;
	
	@Autowired
	public RoleConvert roleConvert;
	
	@Autowired
	public RoleDao roleDao;
	
	@Autowired
	public GenneratePartnerCode partnerCode;

	@Autowired
	public BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Override
	public void registerUser(UserDto user ,String refferedUserPartnerCode) {
		//handle exception
		String userEmailSignup = user.getEmail();
		if(userDao.getUserByEmail(userEmailSignup) != null) {
			throw new EmailAlreadyExitsException("Email " + userEmailSignup + " already exist");
		}
		
		user.setRoles(Arrays.asList(
				roleDao.getRoleByName(SetupDataLoader.ROLE_CUSTOMER)));
		if(!refferedUserPartnerCode.isEmpty()) {
			  user.setReffererUser(
        		userDao.getUserByPartnerCode(refferedUserPartnerCode));
		}
		else {
			 user.setReffererUser(null);
		}
      
        user.setPartnerCode(partnerCode.genneratePartnerCode());
        user.setMoney(BigDecimal.ZERO);
        
        
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        
        userDao.saveUser(user);
        
		
	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(userConvert.userConvertToUserDto(user));
	}

	@Override
	public User getUserById(Integer id) {
		return userConvert.userDtoConvertToUser(userDao.getUserById(id));
	}

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userConvert.userDtoConvertToUser(userDao.getUserByEmail(email));
	}

	@Override
	public User getUserByPartnerCode(String partnerCode) {
		// TODO Auto-generated method stub
		return userConvert.userDtoConvertToUser(userDao.getUserByPartnerCode(partnerCode));
	}

	@Override
	public List<User> getRefferedByUserId(Integer id) {
		return userConvert.usersDtoConvertToUsers(userDao.getRefferedByUserId(id));
	}

	@Override
	public List<UserDto> getAllUser() {
		return userDao.getAllUsers();
	}

	@Override
	public void deleteUser(Integer id) {
		userDao.deleteUser(id);
	}

	
	
	

}
