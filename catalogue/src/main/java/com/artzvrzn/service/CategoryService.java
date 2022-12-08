package com.artzvrzn.service;

import com.artzvrzn.dto.CategoryDto;
import org.springframework.data.domain.Page;

public interface CategoryService {

  CategoryDto getCategory(Long id);

  Page<CategoryDto> getCategories(int page, int size);

  Page<CategoryDto> getCategories(Long parentCategoryId, int page, int size);

  CategoryDto saveCategory(CategoryDto dto);

  CategoryDto updateCategory(Long id, CategoryDto dto);

  void deleteCategory(Long id);
}
