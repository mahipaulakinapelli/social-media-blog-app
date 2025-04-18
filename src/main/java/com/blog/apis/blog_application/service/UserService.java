package com.blog.apis.blog_application.service;

import com.blog.apis.blog_application.dto.UserDto;
import java.util.List;

public interface UserService {

  UserDto createUser(UserDto user);

  UserDto registerNewUser(UserDto user);

  UserDto updateUser(UserDto user, Integer userId);

  UserDto getUserById(Integer userId);

  List<UserDto> getAllUsers();

  void deleteUser(Integer userId);
}
