package com.hstore.vn.service.impl;

import org.springframework.stereotype.Service;

@Service
public class EmailValidator {
    public static final String REGEX_VALIDATE_EMAIL = "^[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
    
    public static boolean isValidEmail(String email) {
    	return email.matches(REGEX_VALIDATE_EMAIL);
    }
}
