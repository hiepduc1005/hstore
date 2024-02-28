package com.hstore.vn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hstore.vn.dao.UserDao;
import com.hstore.vn.exception.auth.EmailAlreadyExitsException;
import com.hstore.vn.payload.UserDto;
import com.hstore.vn.payload.converter.UserConvert;
import com.hstore.vn.payload.request.UserRequest;
import com.hstore.vn.payload.response.ApiResponse;
import com.hstore.vn.payload.response.UserResponse;
import com.hstore.vn.service.UserService;
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	
	@Autowired
	public UserDao userDao;
	
	@Autowired
	public UserService userService;
	
	@Autowired
	public UserConvert userConvert;
	
	@PostMapping
	public ApiResponse<ResponseEntity<String>> createUser(@RequestBody UserRequest userRequest){
		
		if(userDao.getUserByEmail(userRequest.getEmail()) != null ) {
			throw new EmailAlreadyExitsException("email already exist !");
		}
		
		UserDto userDto = new UserDto();
		userDto.setEmail(userRequest.getEmail());
		userDto.setFirstName(userRequest.getFirstName());
		userDto.setLastName(userRequest.getLastName());
		userDto.setPassword(userRequest.getPassword());
		userDto.setRoles(userRequest.getRoles());
		
		userService.registerUser(userDto,"");
		return new ApiResponse<ResponseEntity<String>>("Create user success!",
				new ResponseEntity<>(HttpStatus.OK),0);
	}
	
	@GetMapping("/all")
	public ApiResponse<ResponseEntity<List<UserResponse>>> getAllUser(){
		List<UserResponse> userResponses = userConvert.usersDtoConvertToUsersResponse(userService.getAllUser());
		
		return new ApiResponse<ResponseEntity<List<UserResponse>>>("Get all user success!",
				new ResponseEntity<List<UserResponse>>(userResponses,HttpStatus.OK),0);
	}
	
	@GetMapping
	public ApiResponse<ResponseEntity<UserResponse>> getUserByEmail(@RequestBody String email){
		UserResponse userResponse = userConvert.userDtoConvertToUserResponse(userDao.getUserByEmail(email));
		
		return new ApiResponse<ResponseEntity<UserResponse>>("Get user with email " + email + " success!",
				new ResponseEntity<UserResponse>(userResponse,HttpStatus.OK),0);
	}
	
	@PutMapping
	public ApiResponse<ResponseEntity<?>> updateUser(@RequestBody UserRequest userRequest){
		UserDto userDto = userDao.getUserByEmail(userRequest.getEmail());
		userDto.setEmail(userRequest.getEmail());
		userDto.setFirstName(userRequest.getFirstName());
		userDto.setLastName(userRequest.getLastName());
		userDto.setRoles(userRequest.getRoles());
		userService.updateUser(userConvert.userDtoConvertToUser(userDto));
		
		return new ApiResponse<ResponseEntity<?>>("Update user " + userRequest.getEmail() + " success!",
				new ResponseEntity<>(HttpStatus.OK),0);
	}
	
	@DeleteMapping
	public ApiResponse<ResponseEntity<?>> deleteUser(@RequestBody Integer id){
		userService.deleteUser(id);
		
		return new ApiResponse<ResponseEntity<?>>("Delete user with id " + id + " success !",
				new ResponseEntity<>(HttpStatus.OK),0);
	}
	
	
	
}