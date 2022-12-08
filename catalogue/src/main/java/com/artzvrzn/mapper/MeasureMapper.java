package com.artzvrzn.mapper;

import com.artzvrzn.domain.Measure;
import com.artzvrzn.dto.MeasureDto;
import org.mapstruct.Mapper;

@Mapper
public interface MeasureMapper {

  MeasureDto toDto(Measure source);

  Measure toEntity(MeasureDto source);
}
