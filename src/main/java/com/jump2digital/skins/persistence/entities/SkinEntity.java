package com.jump2digital.skins.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "skins")
@Data
@NoArgsConstructor
public class SkinEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String type;

    private double price;

    private String color;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity owner;

    private boolean available;
}
