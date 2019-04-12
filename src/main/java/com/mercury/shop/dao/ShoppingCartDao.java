package com.mercury.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercury.shop.bean.ShoppingCart;

public interface ShoppingCartDao extends JpaRepository<ShoppingCart, Integer> {

}
