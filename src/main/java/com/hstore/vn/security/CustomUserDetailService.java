package com.hstore.vn.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hstore.vn.dao.UserDao;
import com.hstore.vn.payload.UserDto;

@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	public UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDto userDto = userDao.getUserByEmail(username); 
		if(userDto == null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(userDto.getUsername(), userDto.getPassword(), userDto.getAuthorities());
	}

}
