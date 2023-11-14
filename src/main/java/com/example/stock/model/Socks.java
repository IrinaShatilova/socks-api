package com.example.stock.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "socks")

public class Socks {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "color_id")
    private Color color;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "cotton_part_id")
    private CottonPart cottonPart;

    @Column(name = "quantity")
    private int quantity;

}

