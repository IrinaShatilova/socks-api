package com.example.stock.repository;

import com.example.stock.model.CottonPart;
import com.example.stock.model.Socks;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SocksRepository extends CrudRepository<Socks,Integer> {

    @Query(value = "SELECT * FROM socks WHERE color_id = :colorId AND cotton_part_id = :cottonPartId", nativeQuery = true)
    Socks findSocksByColorIdAndCottonPartId (@Param("colorId") Integer colorId,
                                             @Param("cottonPartId") Integer cottonPartId);

    @Query(value = "SELECT SUM (s.quantity) AS quantity, '0' AS id, '0' AS color_id, '0' AS cotton_part_id FROM socks s JOIN cotton_part cp ON s.cotton_part_id=cp.cotton_part_id WHERE s.color_id = :colorId AND cp.cotton_part >= :cottonPart", nativeQuery = true)
    Socks getStockInfoMoreThan (@Param("colorId") Integer colorId,
                                @Param("cottonPart") Integer cottonPart);

    @Query(value = "SELECT SUM (s.quantity) AS quantity, '0' AS id, '0' AS color_id, '0' AS cotton_part_id FROM socks s JOIN cotton_part cp ON s.cotton_part_id=cp.cotton_part_id WHERE s.color_id = :colorId AND cp.cotton_part <= :cottonPart", nativeQuery = true)
    Socks getStockInfoLessThan (@Param("colorId") Integer colorId,
                                @Param("cottonPart") Integer cottonPart);


}



