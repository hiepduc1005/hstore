package com.hstore.vn.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hstore.vn.dao.CartDao;
import com.hstore.vn.dao.PurchaseDao;
import com.hstore.vn.dao.RoleDao;
import com.hstore.vn.dao.UserDao;
import com.hstore.vn.entity.Cart;
import com.hstore.vn.entity.Role;
import com.hstore.vn.entity.User;
import com.hstore.vn.entity.UsersRoles;
import com.hstore.vn.exception.auth.EmailAlreadyExitsException;
import com.hstore.vn.service.GenneratePartnerCode;
import com.hstore.vn.service.UserService;

@Service
public class DefaultUserService implements UserService{
	
	@Autowired
	public UserDao userDao;
	
	@Autowired
	public RoleDao roleDao;
	
	@Autowired
	public GenneratePartnerCode partnerCode;

	@Autowired
	public BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public CartDao cartDao;
	
	
	@Autowired
	public PurchaseDao purchaseDao;
	
	@Override
	public void registerUser(User user ,String refferedUserPartnerCode) {
		//handle exception
		String userEmailSignup = user.getEmail();
		if(userDao.getUserByEmail(userEmailSignup) != null) {
			throw new EmailAlreadyExitsException("Email " + userEmailSignup + " already exist");
		}
		
		if(!refferedUserPartnerCode.isEmpty()) {
			  user.setReffererUser(
        		userDao.getUserByPartnerCode(refferedUserPartnerCode).getId());
		}
		else {
			 user.setReffererUser(null);
		}
		user.setPartnerCode(partnerCode.genneratePartnerCode());
        user.setMoney(BigDecimal.ZERO);
        
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
		
		Cart cartDto = new Cart();
		cartDto = cartDao.createCart(cartDto);
		user.setCart(cartDto);
		
        User userSaved = userDao.saveUser(user);
        
        cartDto.setUser(userSaved);
		cartDao.updateCart(cartDto);
		
	}
	
	@Override
	public void createUser(User user, String refferedUserPartnerCode) {
		String userEmailSignup = user.getEmail();
		if(userDao.getUserByEmail(userEmailSignup) != null) {
			throw new EmailAlreadyExitsException("Email " + userEmailSignup + " already exist");
		}
		
		if(!refferedUserPartnerCode.isEmpty()) {
			  user.setReffererUser(
      		userDao.getUserByPartnerCode(refferedUserPartnerCode).getId());
		}
		else {
			 user.setReffererUser(null);
		}
		
		user.setPartnerCode(partnerCode.genneratePartnerCode());
        user.setMoney(BigDecimal.ZERO);
        
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
		
		Cart cartDto = new Cart();
		cartDto = cartDao.createCart(cartDto);
		user.setCart(cartDto);
		
        User userSaved = userDao.saveUser(user);
        
        cartDto.setUser(userSaved);
		cartDao.updateCart(cartDto);
		
		
	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	@Override
	public User getUserById(Integer id) {
		return userDao.getUserById(id);
	}

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userDao.getUserByEmail(email);
	}

	@Override
	public User getUserByPartnerCode(String partnerCode) {
		// TODO Auto-generated method stub
		return userDao.getUserByPartnerCode(partnerCode);
	}

	@Override
	public List<User> getRefferedByUserId(Integer id) {
		return userDao.getRefferedByUserId(id);
	}

	@Override
	public List<User> getAllUser() {
		return userDao.getAllUsers();
	}

	@Override
	public void deleteUser(Integer id) {
		User user = getUserById(id);
		User inviteUser = getUserByPartnerCode(user.getPartnerCode());
		if(inviteUser != null) {
			userDao.deleteUser(inviteUser.getId());
		}
		
		purchaseDao.deletePurchaseByUserId(id);
		userDao.deleteUser(id);
	}

	@Override
	public List<Role> getRoleByUser(User user) {
		List<Role> roles = new ArrayList<Role>();
		for(UsersRoles usersRoles : user.getRoles()) {
			roles.add(usersRoles.getRole());
		}
		return roles;
	}

	
	
	

}
