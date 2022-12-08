package com.artzvrzn.dao;

import com.artzvrzn.domain.Measure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasureRepository extends JpaRepository<Measure, Long> {

  Measure findByName(String name);
}
