package com.blog.apis.blog_application.repository;

import com.blog.apis.blog_application.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Integer> {}
