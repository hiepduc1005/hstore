package com.hstore.vn.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hstore.vn.dao.UserDao;
import com.hstore.vn.dto.request.UserEmailRequest;
import com.hstore.vn.dto.request.UserIdRequest;
import com.hstore.vn.dto.request.UserRequest;
import com.hstore.vn.dto.request.UserRequestUpdate;
import com.hstore.vn.dto.response.ApiResponse;
import com.hstore.vn.dto.response.UserResponse;
import com.hstore.vn.entity.User;
import com.hstore.vn.exception.auth.EmailAlreadyExitsException;
import com.hstore.vn.service.UserService;
import com.hstore.vn.service.convert.RoleConvert;
import com.hstore.vn.service.convert.UserConvert;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	public UserDao userDao;

	@Autowired
	public UserService userService;

	@Autowired
	public UserConvert userConvert;

	@Autowired
	public RoleConvert roleConvert;

	@PostMapping
	public ApiResponse<ResponseEntity<String>> createUser(@RequestBody UserRequest userRequest) {

		if (userDao.getUserByEmail(userRequest.getEmail()) != null) {
			throw new EmailAlreadyExitsException("email already exist !");
		}

		User userDto = new User();
		userDto.setEmail(userRequest.getEmail());
		userDto.setFirstName(userRequest.getFirstName());
		userDto.setLastName(userRequest.getLastName());
		userDto.setPassword(userRequest.getPassword());

		userDto.setRoles(roleConvert.rolesRequestConvertToRolesDto(userRequest.getRoles()));

		userService.createUser(userDto, "");

		User userAuthenticated = userService.getAuthenticatedUser();

		LOGGER.info(
				"User with " + userRequest.getEmail() + " was created by user " + userAuthenticated.getEmail());

		return new ApiResponse<ResponseEntity<String>>("Create user success!",
				new ResponseEntity<>(HttpStatus.OK), 0);
	}

	@GetMapping("/all")
	public ApiResponse<ResponseEntity<List<UserResponse>>> getAllUser() {
		List<UserResponse> userResponses = userConvert.usersConvertToUsersResponse(userService.getAllUser());

		return new ApiResponse<ResponseEntity<List<UserResponse>>>("Get all user success!",
				new ResponseEntity<List<UserResponse>>(userResponses, HttpStatus.OK), 0);
	}

	@GetMapping
	public ApiResponse<ResponseEntity<UserResponse>> getUserByEmail(@RequestBody UserEmailRequest userEmailRequest) {
		String email = userEmailRequest.getEmail();
		UserResponse userResponse = userConvert.userConvertToUserResponse(userDao.getUserByEmail(email));

		return new ApiResponse<ResponseEntity<UserResponse>>("Get user with email " + email + " success!",
				new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK), 0);
	}

	@PutMapping
	public ApiResponse<ResponseEntity<String>> updateUser(@RequestBody UserRequestUpdate userRequestUpdate) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();

		User userDto = userDao.getUserByEmail(username);
		userDto.setEmail(userRequestUpdate.getEmail());
		userDto.setFirstName(userRequestUpdate.getFirstName());
		userDto.setLastName(userRequestUpdate.getLastName());

		userDto.setRoles(roleConvert.rolesRequestConvertToRolesDto(userRequestUpdate.getRoles()));

		userDto.setPhoneNum(userRequestUpdate.getPhoneNum());
		userDto.setCreditNum(userRequestUpdate.getCardNum());

		userService.updateUser(userDto);

		return new ApiResponse<ResponseEntity<String>>("Update user " + userRequestUpdate.getEmail() + " success!",
				new ResponseEntity<String>(HttpStatus.OK), 0);
	}

	@DeleteMapping
	public ApiResponse<ResponseEntity<String>> deleteUser(@RequestBody UserIdRequest userIdRequest) {
		Integer userId = userIdRequest.getId();
		userService.deleteUser(userId);

		User userAuthenticated = userService.getAuthenticatedUser();
		LOGGER.info("Deleted user with id " + userId + " by user " + userAuthenticated.getEmail());

		return new ApiResponse<ResponseEntity<String>>("Delete user with id " + userId + " success !",
				new ResponseEntity<String>(HttpStatus.OK), 0);
	}

}