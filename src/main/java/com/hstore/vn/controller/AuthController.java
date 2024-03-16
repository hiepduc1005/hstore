package com.hstore.vn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.hstore.vn.dto.request.LoginRequest;
import com.hstore.vn.dto.request.RegistrationRequest;
import com.hstore.vn.dto.response.ApiResponse;
import com.hstore.vn.dto.response.AuthResponse;
import com.hstore.vn.entity.User;
import com.hstore.vn.exception.auth.EmailAlreadyExitsException;
import com.hstore.vn.security.CustomUserDetailService;
import com.hstore.vn.security.DefaultAuthenticationProvider;
import com.hstore.vn.security.JWTGenerator;
import com.hstore.vn.service.UserService;
import com.hstore.vn.service.impl.EmailValidator;

import jakarta.security.auth.message.AuthException;

@RestController
@RequestMapping(path = "/api/v1/auth")
public class AuthController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

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

	@Autowired
	public JWTGenerator jwtGenerator;

	@PostMapping("/register")
	public ApiResponse<ResponseEntity<String>> register(@RequestBody RegistrationRequest registrationRequest,
			@RequestParam(value = "partnerCode", required = false, defaultValue = "") String partnerCode) {

		if (userDao.getUserByEmail(registrationRequest.getEmail()) != null) {
			throw new EmailAlreadyExitsException("email already exist !");
		}

		User userDto = new User();
		userDto.setEmail(registrationRequest.getEmail());
		userDto.setFirstName(registrationRequest.getFirstName());
		userDto.setLastName(registrationRequest.getLastName());
		userDto.setPassword(registrationRequest.getPassword());

		userService.registerUser(userDto, partnerCode.isEmpty() ? "" : partnerCode);

		ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.OK);

		LOGGER.info("User with email " + registrationRequest.getEmail() + " registerd success !");
		return new ApiResponse<ResponseEntity<String>>("Create user success !", responseEntity, 0);

	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) throws AuthException {
		String email = loginRequest.getEmail();

		if (!EmailValidator.isValidEmail(email)) {
			throw new AuthException("Invalid email");
		}
		// Authentication authentication = new
		// DefaultAuthenticationProvider().authenticate(new
		// UsernamePasswordAuthenticationToken(
		// loginRequest.getEmail(),
		// loginRequest.getPassword()
		// ));
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequest.getEmail(),
						loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtGenerator.generateToken(authentication);

		LOGGER.info("User with email " + email + " login .");

		return new ResponseEntity<AuthResponse>(new AuthResponse(token), HttpStatus.OK);
	}

}
