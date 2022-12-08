package com.artzvrzn.controller.rest;

import com.artzvrzn.dto.ItemDto;
import com.artzvrzn.service.ItemService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {
  private final ItemService itemService;

  @GetMapping(value = {"/{id}", "/{id}/"}, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ItemDto getItem(@PathVariable("id") UUID id) {
    return itemService.getItem(id);
  }

  @GetMapping(value = {"/", ""}, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public Page<ItemDto> getItems(
    @RequestParam("page") int page, @RequestParam("size") int size
  ) {
    return itemService.getItems(page, size);
  }

  @GetMapping(
    value = {"/category/{id}", "/category/{id}/"},
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  @ResponseStatus(HttpStatus.OK)
  public Page<ItemDto> getItems(
    @PathVariable("id") Long categoryId,
    @RequestParam("page") int page,
    @RequestParam("size") int size
  ) {
    return itemService.getItems(categoryId, page, size);
  }

  @PostMapping(
    value = {"/", ""},
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  @ResponseStatus(HttpStatus.CREATED)
  public ItemDto saveItem(@RequestBody ItemDto dto) {
    return itemService.saveItem(dto);
  }

  @PutMapping(
    value = {"/{id}", "/{id}/"},
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE
  )
  @ResponseStatus(HttpStatus.OK)
  public void updateItem(@PathVariable("id") UUID id, @RequestBody ItemDto dto) {
    itemService.updateItem(id, dto);
  }

  @DeleteMapping(value = {"/{id}", "/{id}/"})
  @ResponseStatus(HttpStatus.OK)
  public void deleteItem(@PathVariable("id") UUID id) {
    itemService.deleteItem(id);
  }
}
