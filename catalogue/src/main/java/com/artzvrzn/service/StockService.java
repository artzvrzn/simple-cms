package com.artzvrzn.service;

import com.artzvrzn.dto.StockDto;
import java.util.UUID;
import org.springframework.data.domain.Page;

public interface StockService {

  StockDto getStock(UUID itemId);

  Page<StockDto> getStocks(int page, int size);

  void updateStock(UUID itemId, StockDto dto);
}
