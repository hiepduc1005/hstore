package com.hstore.vn.payload.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hstore.vn.entity.User;
import com.hstore.vn.entity.impl.DefaultUser;
import com.hstore.vn.payload.UserDto;


@Service
public class UserConvert {
	
	@Autowired
	public RoleConvert roleConvert;
	
	@Autowired
	public CartConvert cartConvert;
	
	public User userDtoConvertToUser(UserDto userDto) {
		User user = new DefaultUser();
		
		user.setId(userDto.getId());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setEmail(userDto.getEmail());
		user.setCrediNum(userDto.getCreditNum());
		user.setMoney(userDto.getMoney());
		user.setCart(cartConvert.cartDtoConvertToCart(userDto.getCart()));
		user.setPhoneNum(userDto.getPhoneNum());
		user.setIsEnable(userDto.isEnabled());
		user.setRoles(roleConvert.convertRolesDtoToRoles(userDto.getRoles()));
		user.setPartnerCode(userDto.getPartnerCode());
		user.setReffererUser(userDtoConvertToUser(userDto.getReffererUser()));
		user.setPassword(userDto.getPassword());
		
		return user;
	}
	
	public UserDto userConvertToUserDto(User user) {
		UserDto userDto = new UserDto();
		
		userDto.setId(user.getId());
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setEmail(user.getEmail());
		userDto.setCreditNum(user.getCreditNum());
		userDto.setMoney(user.getMoney());
		userDto.setPhoneNum(user.getPhoneNum());
		userDto.setRoles(roleConvert.convertRolesToRolesDto(user.getRoles()));
		userDto.setPartnerCode(user.getPartnerCode());
		if(user.getReffererUser() != null) {
			userDto.setReffererUser(userConvertToUserDto(user.getReffererUser()));
		}
		userDto.setPassword(user.getPassword());
		
		
		return userDto;
	}
	
	public List<User> usersDtoConvertToUsers(List<UserDto> userDtos){
		List<User> users = new ArrayList<User>();
		
		for(UserDto userDto : userDtos) {
			users.add(userDtoConvertToUser(userDto));
		}
		
		return users;
	}

}
