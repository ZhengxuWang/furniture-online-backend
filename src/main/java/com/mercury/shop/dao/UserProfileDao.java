package com.mercury.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercury.shop.bean.UserProfile;

public interface UserProfileDao extends JpaRepository<UserProfile, Integer> {

}
