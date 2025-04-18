package com.blog.apis.blog_application.repository;

import com.blog.apis.blog_application.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {}
