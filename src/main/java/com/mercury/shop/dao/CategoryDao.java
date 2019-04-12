package com.mercury.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercury.shop.bean.Category;
@(query)
public interface CategoryDao extends JpaRepository<Category, Integer> {
	
}
