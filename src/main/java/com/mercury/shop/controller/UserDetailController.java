package com.mercury.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercury.shop.bean.UserDetail;
import com.mercury.shop.http.Response;
import com.mercury.shop.service.UserDetailService;

@RestController
@RequestMapping("/user-details")
public class UserDetailController {
	
	@Autowired
	UserDetailService userDetailService;

	@PostMapping
	public Response postUserDetails(@RequestBody UserDetail userDetail, Authentication authentication) {
		return userDetailService.addUserDetail(userDetail, authentication);
	}
	
	@PutMapping
	public Response putUserDetails(@RequestBody UserDetail userDetail) {
		return userDetailService.updateUserDetail(userDetail);
	}
	
}
