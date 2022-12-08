package com.artzvrzn.service.impl;

import com.artzvrzn.dao.StockRepository;
import com.artzvrzn.domain.Stock;
import com.artzvrzn.dto.StockDto;
import com.artzvrzn.mapper.StockMapper;
import com.artzvrzn.service.StockService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class, readOnly = true)
public class StockServiceImpl implements StockService {
  private final StockRepository stockRepository;
  private final StockMapper stockMapper;

  @Override
  public StockDto getStock(UUID itemId) {
    Stock entity = stockRepository.findById(itemId).orElseThrow(() -> notFoundException(itemId));
    return stockMapper.toDto(entity);
  }

  @Override
  public Page<StockDto> getStocks(int page, int size) {
    PageRequest request = PageRequest.of(page, size);
    return stockRepository.findAll(request).map(stockMapper::toDto);
  }

  @Override
  @Transactional
  public void updateStock(UUID itemId, StockDto dto) {
    Stock entity = stockRepository.getReferenceById(itemId);
    entity.setQuantity(dto.getQuantity());
  }

  private IllegalStateException notFoundException(UUID id) {
    final String message = "No stock for item %s";
    return new IllegalStateException(String.format(message, id));
  }
}
