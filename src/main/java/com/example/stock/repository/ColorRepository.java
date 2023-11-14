package com.example.stock.repository;
import com.example.stock.model.Color;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ColorRepository extends CrudRepository<Color,Integer> {

    @Query(value = "SELECT * FROM color c WHERE c.color = :color", nativeQuery = true)
    Color findColorByName(@Param("color") String nameColor);
}
