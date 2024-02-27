package com.hstore.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hstore.vn.dao.UserDao;
import com.hstore.vn.exception.auth.EmailAlreadyExitsException;
import com.hstore.vn.payload.UserDto;
import com.hstore.vn.payload.request.LoginRequest;
import com.hstore.vn.payload.request.RegistrationRequest;
import com.hstore.vn.payload.response.ApiResponse;
import com.hstore.vn.payload.response.AuthResponse;
import com.hstore.vn.security.CustomUserDetailService;
import com.hstore.vn.service.UserService;

@RestController
@RequestMapping(path = "/api/v1/auth")
public class AuthController {
	
	@Autowired
	public PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserDao userDao;
	
	@Autowired
	public AuthenticationManager authenticationManager;
	
	@Autowired
	public CustomUserDetailService customUserDetailService;
	
	@Autowired
	public UserService userService;
	
	
	@PostMapping("/register")
	public ApiResponse<ResponseEntity<String>> register(@RequestBody RegistrationRequest registrationRequest ,
			@RequestParam(value = "id", required = false, defaultValue = "") String partnerCode){
		
		if(userDao.getUserByEmail(registrationRequest.getEmail()) != null ) {
			throw new EmailAlreadyExitsException("email already exist !");
		}
		
		UserDto userDto = new UserDto();
		userDto.setEmail(registrationRequest.getEmail());
		userDto.setFirstName(registrationRequest.getFirstName());
		userDto.setLastName(registrationRequest.getLastName());
		userDto.setPassword(registrationRequest.getPassword());
		
		userService.registerUser(userDto, partnerCode.isEmpty() ? "" : partnerCode);
		
		ResponseEntity<String> responseEntity = new ResponseEntity<>( HttpStatus.OK);
		
		return new ApiResponse<ResponseEntity<String>>("Create user success !", responseEntity, 0);
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequest.getEmail(),
						loginRequest.getPassword()
						)
				);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		
		return new ResponseEntity<String>("user is authenticated " , HttpStatus.OK);
	}
	
	

}
