package com.mercury.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercury.shop.bean.Comment;

public interface CommentDao extends JpaRepository<Comment, Integer> {

}
