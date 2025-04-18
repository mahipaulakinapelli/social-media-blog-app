package com.blog.apis.blog_application.controller;

import com.blog.apis.blog_application.dto.ApiResponse;
import com.blog.apis.blog_application.dto.CommentDto;
import com.blog.apis.blog_application.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CommentController {

  @Autowired private CommentService commentService;

  @PostMapping("/post/{postId}/comments")
  public ResponseEntity<CommentDto> createComment(
      @RequestBody CommentDto commentDto, @PathVariable Integer postId) {
    CommentDto createdComment = this.commentService.createComment(commentDto, postId);
    return new ResponseEntity<CommentDto>(createdComment, HttpStatus.CREATED);
  }

  @DeleteMapping("/comments/{commentId}")
  public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId) {
    this.commentService.deleteComment(commentId);

    return new ResponseEntity<ApiResponse>(
        new ApiResponse("Comment deleted successfully!!", true), HttpStatus.OK);
  }
}
