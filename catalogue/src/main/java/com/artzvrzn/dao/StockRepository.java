package com.artzvrzn.dao;

import com.artzvrzn.domain.Stock;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, UUID> {

}
