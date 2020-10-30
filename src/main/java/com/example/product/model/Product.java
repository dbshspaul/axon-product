package com.example.product.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;


@Data
@Entity
@Table(schema = "product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String description;
    private String code;
}
