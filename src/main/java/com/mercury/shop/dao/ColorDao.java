package com.mercury.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercury.shop.bean.Color;

public interface ColorDao extends JpaRepository<Color, Integer> {

}
