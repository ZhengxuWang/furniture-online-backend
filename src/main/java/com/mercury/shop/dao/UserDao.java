package com.mercury.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mercury.shop.bean.User;

public interface UserDao extends JpaRepository<User, Integer> {
	User findByUsername(String username);
	
//	@Query(value = "insert into MSI_USER_MSI_USER_PROFILE(USER_ID, USER_PROFILE_ID) values (?1, 1)", nativeQuery = true)
//	public void setAdminAuthority(int userid);
}
