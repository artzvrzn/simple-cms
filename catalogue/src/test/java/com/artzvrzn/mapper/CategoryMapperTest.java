package com.artzvrzn.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.artzvrzn.domain.Category;
import com.artzvrzn.domain.Item;
import com.artzvrzn.dto.CategoryDto;
import com.artzvrzn.dto.ItemDto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CategoryMapperImpl.class)
public class CategoryMapperTest {
  @Autowired
  private CategoryMapper categoryMapper;

  @Test
  void categoryDtoToEntityMapTest() {
    Long categoryId = 1L;
    Long parentId = 2L;
    CategoryDto dto = CategoryDto.builder()
      .id(categoryId)
      .name("categoryName")
      .parentId(parentId)
      .parentName("parentName")
      .build();
    Category entity = categoryMapper.toEntity(dto);
    assertEquals(dto.getName(), entity.getName());
    assertEquals(dto.getId(), entity.getId());
    assertNull(entity.getParent());
  }

  @Test
  void categoryEntityToDtoMapTest() {

    Category parentEntity = Category.builder()
      .id(1L)
      .name("parentName")
      .parent(null)
      .build();

    Category entity = Category.builder()
      .id(2L)
      .name("categoryName")
      .parent(parentEntity)
      .build();

    CategoryDto dto = categoryMapper.toDto(entity);

    assertEquals(entity.getName(), dto.getName());
    assertEquals(entity.getId(), dto.getId());
    assertEquals(entity.getParent().getId(), dto.getParentId());
    assertEquals(entity.getParent().getName(), dto.getParentName());
    assertNull(entity.getParent());
  }
}
