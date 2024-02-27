package com.hstore.vn.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hstore.vn.payload.UserDto;
import com.hstore.vn.payload.request.UserRequest;
import com.hstore.vn.payload.response.ApiResponse;
@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/user")
    public String getUser() {
        return "Hello, authenticated user!";
    }

    @GetMapping("/admin")
    public String getAdmin() {
        return "Hello, admin!";
    }
}