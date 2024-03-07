package com.hstore.vn.security;

import com.hstore.vn.entity.Privilege;
import com.hstore.vn.entity.Role;
import com.hstore.vn.entity.User;
import com.hstore.vn.service.RoleService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hstore.vn.dao.UserDao;

@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	public UserDao userDao;
	
	@Autowired
	public RoleService roleService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.getUserByEmail(username); 
		if(user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),getAuthorities(user));
	}
	
	
	public Collection<? extends GrantedAuthority> getAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		List<String> privileges = getPrivileges(user);
		
		for(String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return  authorities;
	}
	
	public List<String> getPrivileges(User user ){
        List<Privilege> privilegeDtos = new ArrayList<Privilege>();
        List<Role> roles = userDao.getRoleByUser(user);
		List<String> privileges = new ArrayList<String>();
		for(Role role : roles) {
			privilegeDtos.addAll(roleService.getPrivilegesByRole(role));
			privileges.add(role.getName());
		}
		
		for(Privilege privilege : privilegeDtos ) {
			privileges.add(privilege.getName());
		}
		
		return privileges;
	}

}
