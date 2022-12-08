package com.artzvrzn.service.impl;

import com.artzvrzn.dao.MeasureRepository;
import com.artzvrzn.domain.Item;
import com.artzvrzn.domain.Measure;
import com.artzvrzn.dto.MeasureDto;
import com.artzvrzn.mapper.MeasureMapper;
import com.artzvrzn.service.MeasureService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeasureServiceImpl implements MeasureService {
  private final MeasureRepository measureRepository;
  private final MeasureMapper measureMapper;

  @Override
  public MeasureDto getMeasure(Long id) {
    Measure entity = measureRepository.findById(id).orElseThrow(() -> notFoundException(id));
    return measureMapper.toDto(entity);
  }

  @Override
  public Page<MeasureDto> getMeasures(int page, int size) {
    PageRequest request = PageRequest.of(page, size);
    Page<Measure> measures = measureRepository.findAll(request);
    return measures.map(measureMapper::toDto);
  }

  @Override
  public void createMeasure(MeasureDto dto) {
    Measure measure = measureMapper.toEntity(dto);
    measureRepository.save(measure);
  }

  @Override
  public void updateMeasure(Long id, MeasureDto dto) {

  }

  @Override
  public void deleteMeasure(Long id) {

  }

  private IllegalArgumentException notFoundException(Long id) {
    final String message = "Measure with id %s doesn't exist!";
    return new IllegalArgumentException(String.format(message, id));
  }
}
