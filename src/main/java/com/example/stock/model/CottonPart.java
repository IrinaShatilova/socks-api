package com.example.stock.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "cotton_part")
public class CottonPart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cotton_part_id")
    private Integer cottonPartId;
    @Column(name = "cotton_part")
    private Integer cottonPart;

}
