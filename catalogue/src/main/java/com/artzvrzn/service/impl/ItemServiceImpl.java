package com.artzvrzn.service.impl;

import com.artzvrzn.dao.CategoryRepository;
import com.artzvrzn.dao.ItemRepository;
import com.artzvrzn.dao.MeasureRepository;
import com.artzvrzn.dao.StockRepository;
import com.artzvrzn.domain.Category;
import com.artzvrzn.domain.Item;
import com.artzvrzn.domain.Measure;
import com.artzvrzn.domain.Stock;
import com.artzvrzn.dto.ItemDto;
import com.artzvrzn.mapper.ItemMapper;
import com.artzvrzn.service.ItemService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class, readOnly = true)
public class ItemServiceImpl implements ItemService {
  private final ItemRepository itemRepository;
  private final CategoryRepository categoryRepository;
  private final MeasureRepository measureRepository;
  private final StockRepository stockRepository;
  private final ItemMapper itemMapper;

  @Override
  public ItemDto getItem(UUID id) {
    Item entity = itemRepository.findById(id).orElseThrow(() -> notFoundException(id));
    return itemMapper.toDto(entity);
  }

  @Override
  public Page<ItemDto> getItems(int page, int size) {
    PageRequest request = PageRequest.of(page, size);
    Page<Item> items = itemRepository.findAll(request);
    return items.map(itemMapper::toDto);
  }

  @Override
  public Page<ItemDto> getItems(Long categoryId, int page, int size) {
    PageRequest request = PageRequest.of(page, size);
    Page<Item> items = itemRepository.findAllByCategory_Id(categoryId, request);
    return items.map(itemMapper::toDto);
  }

  @Override
  @Transactional
  public ItemDto saveItem(ItemDto dto) {
    Category categoryEntity = categoryRepository.getReferenceById(dto.getCategoryId());
    Measure measureEntity = measureRepository.getReferenceById(dto.getMeasureId());

    Item itemEntity = itemRepository.save(
      Item.builder()
        .name(dto.getName())
        .description(dto.getDescription())
        .measure(measureEntity)
        .category(categoryEntity)
        .build()
    );

    stockRepository.save(
      Stock.builder()
        .item(itemEntity)
        .quantity(0)
        .build()
    );

    return itemMapper.toDto(itemEntity);
  }

  @Override
  @Transactional
  public void updateItem(UUID id, ItemDto dto) {
    Item entity = itemRepository.getReferenceById(id);
    if (dto.getMeasureId() != null && dto.getMeasureId().equals(entity.getMeasure().getId())) {
      Measure measureEntity = measureRepository.getReferenceById(dto.getMeasureId());
      entity.setMeasure(measureEntity);
    }
    if (dto.getCategoryId() != null && dto.getCategoryId().equals(entity.getCategory().getId())) {
      Category categoryEntity = categoryRepository.getReferenceById(dto.getCategoryId());
      entity.setCategory(categoryEntity);
    }
    entity.setName(dto.getName());
    entity.setDescription(dto.getDescription());
  }

  @Override
  public void deleteItem(UUID id) {
    itemRepository.deleteById(id);
  }

  private IllegalArgumentException notFoundException(UUID id) {
    final String message = "Item with id %s doesn't exist!";
    return new IllegalArgumentException(String.format(message, id));
  }
}
