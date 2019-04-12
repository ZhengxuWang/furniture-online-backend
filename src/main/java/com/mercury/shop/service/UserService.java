package com.mercury.shop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.shop.bean.User;
import com.mercury.shop.bean.UserProfile;
import com.mercury.shop.dao.UserDao;
import com.mercury.shop.dao.UserProfileDao;
import com.mercury.shop.http.Response;
import com.mercury.shop.security.SecurityUtils;

@Service
@Transactional
public class UserService {

	@Autowired
	UserDao userDao;
	@Autowired
	UserProfileDao userprofileDao;
	@Autowired
	PasswordEncoder passwordEncoder;
	public Response register(User user) {
		try {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			List<UserProfile> profiles = new ArrayList<UserProfile>();
			profiles.add(new UserProfile(2));
			user.setProfiles(profiles);
			userDao.save(user);
			// TODO: send email. assume username is the email for now
			return new Response(true);
		} catch (Exception e) {
			return new Response(false, 400, e.getMessage());
		}
		
	}
	
	public Response changePassword(User user, Authentication authentication) {
		if(user.getUsername().equals(authentication.getName()) || SecurityUtils.isAdmin(authentication.getAuthorities())) {
			User u = userDao.findByUsername(user.getUsername());
			u.setPassword(passwordEncoder.encode(user.getPassword()));
			userDao.save(u);
		}else {
			return new Response(false);
		}
		return new Response(true);
	}
	
	public Response deleteUser(int id) {
		if(userDao.findById(id).get() != null) {
			userDao.deleteById(id);
			return new Response(true);
		}else {
			return new Response(false, "User is not found!");
		}
	}

	public Response setAdminAuthority(int userid) {
		Optional<User> op = userDao.findById(userid);
		if(op.isPresent()) {
			UserProfile up = new UserProfile(1);
			User u = op.get();
			List<UserProfile> ups = u.getProfiles();
			ups.add(up);
			userDao.save(u);
			return new Response(true);
		}
		return new Response(false);
	}
	
}