package com.artzvrzn.controller.rest;

import com.artzvrzn.dto.CategoryDto;
import com.artzvrzn.service.CategoryService;
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
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
  private final CategoryService categoryService;

  @GetMapping(value = {"/{id}", "/{id}/"}, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public CategoryDto getCategory(@PathVariable("id") long id) {
    return categoryService.getCategory(id);
  }

  @GetMapping(value = {"/", ""}, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public Page<CategoryDto> getCategories(
    @RequestParam("page") int page, @RequestParam("size") int size
  ) {
    return categoryService.getCategories(page, size);
  }

  @GetMapping(value = {"/sub/{id}", "/sub/{id}/"}, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public Page<CategoryDto> getCategories(
    @PathVariable("id") long id,
    @RequestParam("page") int page,
    @RequestParam("size") int size
  ) {
    return categoryService.getCategories(id, page, size);
  }

  @PostMapping(
    value = {"/", ""},
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  @ResponseStatus(HttpStatus.CREATED)
  public CategoryDto createCategory(@RequestBody CategoryDto dto) {
    return categoryService.saveCategory(dto);
  }

  @PutMapping(
    value = {"/{id}", "/{id}/"},
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE
  )
  @ResponseStatus(HttpStatus.OK)
  public CategoryDto updateCategory(@PathVariable("id") Long id, @RequestBody CategoryDto dto) {
    return categoryService.updateCategory(id, dto);
  }

  @DeleteMapping(value = {"/{id}", "/{id}/"})
  @ResponseStatus(HttpStatus.OK)
  public void deleteCategory(@PathVariable("id") Long id) {
    categoryService.deleteCategory(id);
  }
}
