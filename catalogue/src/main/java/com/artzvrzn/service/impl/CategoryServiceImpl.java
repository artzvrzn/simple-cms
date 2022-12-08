package com.artzvrzn.service.impl;

import com.artzvrzn.dao.CategoryRepository;
import com.artzvrzn.domain.Category;
import com.artzvrzn.dto.CategoryDto;
import com.artzvrzn.mapper.CategoryMapper;
import com.artzvrzn.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class, readOnly = true)
public class CategoryServiceImpl implements CategoryService {
  private final CategoryRepository categoryRepository;
  private final CategoryMapper categoryMapper;

  @Override
  public CategoryDto getCategory(Long id) {
    Category entity = categoryRepository.findById(id).orElseThrow(() -> notFoundException(id));
    return categoryMapper.toDto(entity);
  }

  @Override
  public Page<CategoryDto> getCategories(int page, int size) {
    PageRequest pageRequest = PageRequest.of(page, size);
    Page<Category> categories = categoryRepository.findAll(pageRequest);
    return categories.map(categoryMapper::toDto);
  }

  @Override
  public Page<CategoryDto> getCategories(Long parentCategoryId, int page, int size) {
    PageRequest pageRequest = PageRequest.of(page, size);
    return categoryRepository.findAllByParent_Id(parentCategoryId, pageRequest)
      .map(categoryMapper::toDto);
  }

  @Override
  @Transactional
  public CategoryDto saveCategory(CategoryDto dto) {
    Category parentEntity =
      dto.getParentId() == null ? null : categoryRepository.getReferenceById(dto.getParentId());
    Category entity = Category.builder()
      .name(dto.getName())
      .parent(parentEntity)
      .build();
    entity = categoryRepository.save(entity);
    return categoryMapper.toDto(entity);
  }

  @Override
  @Transactional
  public CategoryDto updateCategory(Long id, CategoryDto dto) {
    Category entity = categoryRepository.findById(id).orElseThrow(() -> notFoundException(id));
    if (dto.getParentId() != null && !dto.getParentId().equals(entity.getParent().getId())) {
      Category newParentEntity = categoryRepository.getReferenceById(dto.getParentId());
      entity.setParent(newParentEntity);
    }
    entity.setName(dto.getName());
    entity = categoryRepository.save(entity);
    return categoryMapper.toDto(entity);
  }

  @Override
  public void deleteCategory(Long id) {
    categoryRepository.deleteById(id);
  }

  private IllegalArgumentException notFoundException(Long id) {
    final String message = "Category with id %s doesn't exist!";
    return new IllegalArgumentException(String.format(message, id));
  }
}
