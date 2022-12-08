package com.artzvrzn.controller.rest;

import com.artzvrzn.dto.MeasureDto;
import com.artzvrzn.service.MeasureService;
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
@RequestMapping("/measure")
@RequiredArgsConstructor
public class MeasureController {
  private final MeasureService measureService;

  @GetMapping(value = {"/{id}", "/{id}/"}, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public MeasureDto getMeasure(@PathVariable("id") long id) {
    return measureService.getMeasure(id);
  }

  @GetMapping(value = {"/", ""}, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public Page<MeasureDto> getMeasures(
    @RequestParam("page") int page, @RequestParam("size") int size
  ) {
    return measureService.getMeasures(page, size);
  }

  @PostMapping(
    value = {"/", ""},
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  @ResponseStatus(HttpStatus.CREATED)
  public void saveMeasure(@RequestBody MeasureDto dto) {
    measureService.createMeasure(dto);
  }

  @PutMapping(
    value = {"/{id}", "/{id}/"},
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE
  )
  @ResponseStatus(HttpStatus.OK)
  public void updateMeasure(@PathVariable("id") long id, @RequestBody MeasureDto dto) {
    measureService.updateMeasure(id, dto);
  }

  @DeleteMapping(value = {"/{id}", "/{id}/"})
  @ResponseStatus(HttpStatus.OK)
  public void deleteItem(@PathVariable("id") long id) {
    measureService.deleteMeasure(id);
  }
}
