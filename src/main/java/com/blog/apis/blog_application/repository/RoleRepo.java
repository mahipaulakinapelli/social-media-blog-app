package com.blog.apis.blog_application.repository;

import com.blog.apis.blog_application.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Integer> {}
