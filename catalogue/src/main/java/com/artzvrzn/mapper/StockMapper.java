package com.artzvrzn.mapper;

import com.artzvrzn.domain.Stock;
import com.artzvrzn.dto.StockDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface StockMapper {

  @Mapping(target = "itemId", source = "source.id")
  StockDto toDto(Stock source);

  @Mapping(target = "id", source = "source.itemId")
  @Mapping(target = "item", ignore = true)
  Stock toEntity(StockDto source);
}
