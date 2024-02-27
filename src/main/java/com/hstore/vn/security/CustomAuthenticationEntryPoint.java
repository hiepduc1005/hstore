package com.hstore.vn.security;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		    ResponseEntity<Object> entity = new ResponseEntity<>("Unauthorized access", HttpStatus.UNAUTHORIZED);
	        response.setStatus(HttpStatus.UNAUTHORIZED.value());
	        response.setContentType("application/json");
	        response.getWriter().write(entity.toString());
	        response.getWriter().flush();
		
	}

}
