package com.mercury.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.mercury.shop.bean.UserProfile;
import com.mercury.shop.dao.UserDao;
import com.mercury.shop.http.AuthenticationSuccessResponse;
import com.mercury.shop.http.Response;

@Service
public class AuthService {
	
	@Autowired
	UserDao userDao;

	public Response checklogin(Authentication authentication) {
		if (authentication != null) {
			Response response = new AuthenticationSuccessResponse(true, 200, "Logged In!", userDao.findByUsername(authentication.getName()));
			return response;
		} else {
			return new Response(false);
		}
	}
	public Response checkAdmin(Authentication authentication) {
		
		if (authentication != null) {
			List<UserProfile> profiles = userDao.findByUsername(authentication.getName()).getProfiles();
			for(UserProfile profile: profiles) {
				if(profile.getId() == 1) {
					return new AuthenticationSuccessResponse(true, 200, "Logged In!", userDao.findByUsername(authentication.getName()));
				}
			}
			return new Response(false);
		} else {
			return new Response(false);
		}
	}

}
