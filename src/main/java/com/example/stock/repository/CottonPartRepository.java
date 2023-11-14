package com.example.stock.repository;

import com.example.stock.model.Color;
import com.example.stock.model.CottonPart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CottonPartRepository extends CrudRepository<CottonPart,Integer> {

    @Query(value = "SELECT * FROM cotton_part cp WHERE cp.cotton_part = :cottonPart", nativeQuery = true)
    CottonPart findCottonPartByName(@Param("cottonPart") Integer nameCottonPart);
}
