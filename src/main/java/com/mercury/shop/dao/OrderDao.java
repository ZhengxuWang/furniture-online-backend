package com.mercury.shop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercury.shop.bean.Order;

public interface OrderDao extends JpaRepository<Order, Integer> {
	public List<Order> findByUserId(int id);
}
