package com.example.stock.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "color")
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "color_id")
    private Integer colorId;
    @Column(name = "color", nullable = false, length = 16)
    private String color;

}

