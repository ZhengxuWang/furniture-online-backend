package com.mercury.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercury.shop.http.Response;
import com.mercury.shop.service.AuthService;

@RestController
public class AuthController {
	
	@Autowired
	AuthService authService;
	
	@GetMapping("/checklogin")
	public Response checklogin(Authentication authentication) {
		return authService.checklogin(authentication);
	}
	@GetMapping("/checkadmin")
	public Response checkAdmin(Authentication authentication) {
		return authService.checkAdmin(authentication);
	}
}
