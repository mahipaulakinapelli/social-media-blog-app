package com.blog.apis.blog_application.service.impl;

import com.blog.apis.blog_application.dto.CommentDto;
import com.blog.apis.blog_application.exceptions.ResourceNotFoundException;
import com.blog.apis.blog_application.model.Comment;
import com.blog.apis.blog_application.model.Post;
import com.blog.apis.blog_application.repository.CommentRepo;
import com.blog.apis.blog_application.repository.PostRepo;
import com.blog.apis.blog_application.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

  @Autowired private PostRepo postRepo;

  @Autowired private CommentRepo commentRepo;

  @Autowired private ModelMapper modelMapper;

  @Override
  public CommentDto createComment(CommentDto commentDto, Integer postId) {

    Post post =
        this.postRepo
            .findById(postId)
            .orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));

    Comment comment = this.modelMapper.map(commentDto, Comment.class);

    comment.setPost(post);
    Comment savedComment = this.commentRepo.save(comment);
    return this.modelMapper.map(savedComment, CommentDto.class);
  }

  @Override
  public void deleteComment(Integer commentId) {
    Comment com =
        commentRepo
            .findById(commentId)
            .orElseThrow(() -> new ResourceNotFoundException("Comment", "comment id", commentId));
    this.commentRepo.delete(com);
  }
}
