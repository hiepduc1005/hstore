package com.hstore.vn.service.convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hstore.vn.dto.response.UserResponse;
import com.hstore.vn.entity.User;


@Service
public class UserConvert {
	
	@Autowired
	public RoleConvert roleConvert;
	
	@Autowired
	public CartConvert cartConvert;
	
	
	public UserResponse userConvertToUserResponse(User user) {
		if(user == null) {
			return null;
		}
		
		Integer userReffereId = null;
		if(user.getReffererUser() == null){
			userReffereId = null;
		}
		
		UserResponse userResponse = new UserResponse(
				user.getId(),
				user.getFirstName(),
				user.getLastName(),
				user.getEmail(),
				user.getPhoneNum(),

				roleConvert.rolesConvertToRolesResponse(user.getRoles()),
				cartConvert.cartConvertToCartResponse(user.getCart()),
				user.getMoney(),
				user.getCreditNum(),
				user.getPartnerCode(),
				userReffereId,
			    false,
			    true);
		
		return userResponse;
				
	}
	
	public List<UserResponse> usersConvertToUsersResponse(List<User> users) {
		List<UserResponse> userResponses = new ArrayList<UserResponse>();
		for(User user : users) {
			userResponses.add(userConvertToUserResponse(user));
		}
		
		return userResponses;
	}

}
