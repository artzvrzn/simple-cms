package com.artzvrzn.controller.rest;

import com.artzvrzn.dto.StockDto;
import com.artzvrzn.service.StockService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
public class StockController implements StockService {
  private final StockService stockService;

  @GetMapping(value = {"/", ""}, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public StockDto getStock(UUID itemId) {
    return stockService.getStock(itemId);
  }

  @GetMapping(value = {"/", ""}, produces = MediaType.APPLICATION_JSON_VALUE)
  public Page<StockDto> getStocks(@RequestParam("page") int page, @RequestParam("size") int size) {
    return stockService.getStocks(page, size);
  }

  @PutMapping(value = {"/{id}", "/{id}/"}, consumes = MediaType.APPLICATION_JSON_VALUE)
  public void updateStock(@PathVariable("id") UUID itemId, @RequestBody StockDto dto) {
    stockService.updateStock(itemId, dto);
  }
}
