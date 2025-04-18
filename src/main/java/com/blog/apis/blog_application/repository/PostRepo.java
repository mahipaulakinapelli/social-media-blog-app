package com.blog.apis.blog_application.repository;

import com.blog.apis.blog_application.model.Category;
import com.blog.apis.blog_application.model.Post;
import com.blog.apis.blog_application.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Integer> {

  List<Post> findByUser(User user);

  List<Post> findByCategory(Category category);

  List<Post> findByTitleContaining(String title);
}
