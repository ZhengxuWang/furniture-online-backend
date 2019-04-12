package com.mercury.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.mercury.shop.bean.User;
import com.mercury.shop.bean.UserDetail;
import com.mercury.shop.dao.UserDao;
import com.mercury.shop.dao.UserDetailDao;
import com.mercury.shop.http.Response;
import com.mercury.shop.http.UserDetailResponse;

@Service
public class UserDetailService {

	@Autowired
	UserDao userDao;
	
	@Autowired
	UserDetailDao userDetailDao;
	
	public Response addUserDetail(UserDetail userDetail, Authentication authentication) {
		User user = userDao.findByUsername(authentication.getName());
		userDetail.setUser(user);
		return new UserDetailResponse(true, userDetailDao.save(userDetail));
	}
	public Response updateUserDetail(UserDetail userDetail) {
		UserDetail ud = userDetailDao.findById(userDetail.getId()).get();
		userDetail.setUser(ud.getUser());
		ud = userDetail;
		userDetailDao.save(ud);
		return new Response(true);
	}
}
