package com.blog.apis.blog_application.service.impl;

import com.blog.apis.blog_application.config.AppConstants;
import com.blog.apis.blog_application.dto.UserDto;
import com.blog.apis.blog_application.exceptions.ResourceNotFoundException;
import com.blog.apis.blog_application.model.Role;
import com.blog.apis.blog_application.model.User;
import com.blog.apis.blog_application.repository.RoleRepo;
import com.blog.apis.blog_application.repository.UserRepo;
import com.blog.apis.blog_application.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired private UserRepo userRepo;

  @Autowired private ModelMapper modelMapper;

  @Autowired private PasswordEncoder passwordEncoder;

  @Autowired private RoleRepo roleRepo;

  @Override
  public UserDto createUser(UserDto userDto) {

    User user = this.dtoToUser(userDto);

    User savedUser = userRepo.save(user);

    return this.userToDto(savedUser);
  }

  @Override
  public UserDto updateUser(UserDto userDto, Integer userId) {
    User user =
        this.userRepo
            .findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

    user.setName(userDto.getName());
    user.setEmail(userDto.getEmail());
    user.setAbout(userDto.getAbout());
    user.setPassword(userDto.getPassword());

    User updatedUser = this.userRepo.save(user);

    return this.userToDto(updatedUser);
  }

  @Override
  public UserDto getUserById(Integer userId) {
    User user =
        this.userRepo
            .findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    return this.userToDto(user);
  }

  @Override
  public List<UserDto> getAllUsers() {
    List<User> users = this.userRepo.findAll();
    List<UserDto> userDtos =
        users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());

    return userDtos;
  }

  @Override
  public void deleteUser(Integer userId) {
    User user =
        this.userRepo
            .findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    System.out.println("deleting user.....");
    this.userRepo.delete(user);
  }

  public User dtoToUser(UserDto userDto) {
    // (kis object ko convert karna h , kis class ke object me convert karna h)
    User user = this.modelMapper.map(userDto, User.class);
    return user;
  }

  public UserDto userToDto(User user) {
    UserDto userDto = this.modelMapper.map(user, UserDto.class);
    return userDto;
  }

  @Override
  public UserDto registerNewUser(UserDto userDto) {

    User user = this.modelMapper.map(userDto, User.class);

    user.setPassword(this.passwordEncoder.encode(user.getPassword()));

    Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();

    user.getRoles().add(role);

    User newUser = this.userRepo.save(user);

    return this.modelMapper.map(newUser, UserDto.class);
  }
}
