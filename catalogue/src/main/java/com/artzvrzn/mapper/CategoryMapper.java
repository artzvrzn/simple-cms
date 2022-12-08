package com.artzvrzn.mapper;

import com.artzvrzn.domain.Category;
import com.artzvrzn.dto.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CategoryMapper {

  @Mapping(target = "parent", ignore = true)
  Category toEntity(CategoryDto source);

  @Mapping(
    target = "parentId",
    expression = "java(source.getParent() == null ? null : source.getParent().getId())"
  )
  @Mapping(
    target = "parentName",
    expression = "java(source.getParent() == null ? null : source.getParent().getName())"
  )
  CategoryDto toDto(Category source);
}
