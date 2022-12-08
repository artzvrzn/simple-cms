package com.artzvrzn.mapper;

import com.artzvrzn.domain.Item;
import com.artzvrzn.dto.ItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ItemMapper {

  @Mapping(
    target = "categoryId",
    expression = "java(source.getCategory() == null ? null : source.getCategory().getId())"
  )
  @Mapping(
    target = "measureId",
    expression = "java(source.getMeasure() == null ? null : source.getMeasure().getId())"
  )
  ItemDto toDto(Item source);

  @Mapping(target = "category", ignore = true)
  @Mapping(target = "measure", ignore = true)
  Item toEntity(ItemDto source);
}
