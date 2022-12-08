package com.artzvrzn.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.artzvrzn.domain.Category;
import com.artzvrzn.domain.Item;
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
@ContextConfiguration(classes = ItemMapperImpl.class)
public class ItemMapperTest {
  @Autowired
  private ItemMapper itemMapper;

  @Test
  void itemDtoToEntityMapTest() {
    UUID itemId = UUID.randomUUID();
    Long categoryId = 1L;
    LocalDateTime now = LocalDateTime.now();
    Category category = Category.builder()
      .id(categoryId)
      .name("categoryName")
      .parent(null)
      .build();
    Item itemEntity = Item.builder()
      .id(itemId)
      .name("itemName")
      .description("itemDescription")
      .dtCreated(now)
      .dtUpdated(now)
      .category(category)
      .build();
    ItemDto itemDto = itemMapper.toDto(itemEntity);

    assertEquals(itemEntity.getId(), itemDto.getId());
    assertEquals(itemEntity.getName(), itemDto.getName());
    assertEquals(itemEntity.getDescription(), itemDto.getDescription());
    assertEquals(itemEntity.getDtUpdated(), itemDto.getDtUpdated());
    assertEquals(itemEntity.getDtCreated(), itemDto.getDtCreated());

    assertEquals(itemEntity.getCategory().getId(), itemDto.getCategoryId());
  }

  @Test
  void itemEntityToDtoMapTest() {
    UUID itemId = UUID.randomUUID();
    Long categoryId = 1L;
    LocalDateTime now = LocalDateTime.now();
    ItemDto dto = ItemDto.builder()
      .id(itemId)
      .name("itemName")
      .description("itemDescription")
      .dtCreated(now)
      .dtUpdated(now)
      .categoryId(categoryId)
      .build();
    Item entity = itemMapper.toEntity(dto);

    assertEquals(dto.getId(), entity.getId());
    assertEquals(dto.getName(), entity.getName());
    assertEquals(dto.getDescription(), entity.getDescription());
    assertEquals(dto.getDtUpdated(), entity.getDtUpdated());
    assertEquals(dto.getDtCreated(), entity.getDtCreated());

    assertNull(entity.getCategory());
  }
}
