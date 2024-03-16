package com.hstore.vn.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class DefaultAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	public PasswordEncoder passwordEncoder;
	
	@Autowired
	public CustomUserDetailService customUserDetailService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userName = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		System.out.println("dấdadsadasdsa");
		
		UserDetails user = customUserDetailService.loadUserByUsername(userName);
		
		if(user == null) {
			throw new BadCredentialsException("Incorrect login/password pair");
		}

		if (isPasswordValid(user, password)) {
			return new UsernamePasswordAuthenticationToken(userName, password, user.getAuthorities());
		} else {
			throw new BadCredentialsException("Incorrect login/password pair");
		}
	}
	
	private boolean isPasswordValid(UserDetails user , String password) {
		return passwordEncoder.matches(password, user.getPassword());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
	