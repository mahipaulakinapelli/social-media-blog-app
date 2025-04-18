package com.blog.apis.blog_application.repository;

import com.blog.apis.blog_application.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);
}
