package com.blog.apis.blog_application.dto;

import lombok.Data;

@Data
public class JwtAuthRequest {

  private String username;

  private String password;
}
