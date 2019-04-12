package com.mercury.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercury.shop.bean.OrderProduct;

public interface OrderProductDao extends JpaRepository<OrderProduct, Integer> {

}
