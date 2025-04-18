package com.blog.apis.blog_application.service.impl;

import com.blog.apis.blog_application.dto.CategoryDto;
import com.blog.apis.blog_application.exceptions.ResourceNotFoundException;
import com.blog.apis.blog_application.model.Category;
import com.blog.apis.blog_application.repository.CategoryRepo;
import com.blog.apis.blog_application.service.CategoryService;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

  @Autowired private CategoryRepo categoryRepo;

  @Autowired private ModelMapper modelMapper;

  @Override
  public CategoryDto createCategory(CategoryDto categoryDto) {

    Category cat = this.modelMapper.map(categoryDto, Category.class);
    Category addedCat = this.categoryRepo.save(cat);
    return this.modelMapper.map(addedCat, CategoryDto.class);
  }

  @Override
  public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {

    Category cat =
        this.categoryRepo
            .findById(categoryId)
            .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

    cat.setCategoryTitle(categoryDto.getCategoryTitle());
    cat.setCategoryDescription(categoryDto.getCategoryDescription());
    Category updatedCat = this.categoryRepo.save(cat);
    return this.modelMapper.map(updatedCat, CategoryDto.class);
  }

  @Override
  public void deleteCategory(Integer categoryId) {
    Category cat =
        this.categoryRepo
            .findById(categoryId)
            .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
    this.categoryRepo.delete(cat);
  }

  @Override
  public CategoryDto getCategory(Integer categoryId) {
    Category cat =
        this.categoryRepo
            .findById(categoryId)
            .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
    return this.modelMapper.map(cat, CategoryDto.class);
  }

  @Override
  public List<CategoryDto> getCategories() {
    List<Category> categories = this.categoryRepo.findAll();

    List<CategoryDto> catDtos =
        categories.stream()
            .map(cat -> this.modelMapper.map(cat, CategoryDto.class))
            .collect(Collectors.toList());
    return catDtos;
  }
}
