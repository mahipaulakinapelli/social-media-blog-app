package com.blog.apis.blog_application.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostResponse {

  private List<PostDto> content;
  private int pageNumber;
  private int pageSize;
  private int totalPages;
  private long totalelements;
  private boolean lastPage;
}
