package com.mercury.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercury.shop.bean.Product;

public interface ProductDao extends JpaRepository<Product, Integer> {
	public Product findByName(String name);
}
