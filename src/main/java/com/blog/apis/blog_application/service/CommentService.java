package com.blog.apis.blog_application.service;

import com.blog.apis.blog_application.dto.CommentDto;

public interface CommentService {

  CommentDto createComment(CommentDto commentDto, Integer postId);

  void deleteComment(Integer commentId);
}
