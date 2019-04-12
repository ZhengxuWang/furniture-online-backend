package com.mercury.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercury.shop.bean.UserDetail;

public interface UserDetailDao extends JpaRepository<UserDetail, Integer> {

}
