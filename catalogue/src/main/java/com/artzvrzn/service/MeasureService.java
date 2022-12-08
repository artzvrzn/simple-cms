package com.artzvrzn.service;

import com.artzvrzn.dto.MeasureDto;
import org.springframework.data.domain.Page;

public interface MeasureService {

  MeasureDto getMeasure(Long id);

  Page<MeasureDto> getMeasures(int page, int size);

  void createMeasure(MeasureDto dto);

  void updateMeasure(Long id, MeasureDto dto);

  void deleteMeasure(Long id);
}
