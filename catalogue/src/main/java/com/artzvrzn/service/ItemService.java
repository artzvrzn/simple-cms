package com.artzvrzn.service;

import com.artzvrzn.dto.ItemDto;
import java.util.UUID;
import org.springframework.data.domain.Page;

public interface ItemService {

  ItemDto getItem(UUID id);

  Page<ItemDto> getItems(int page, int size);

  Page<ItemDto> getItems(Long categoryId, int page, int size);

  ItemDto saveItem(ItemDto dto);

  void updateItem(UUID id, ItemDto dto);

  void deleteItem(UUID id);
}
