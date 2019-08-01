package io.github.brunogabriel.apimicroservice.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by brunogabriel on 2019-06-12.
 */
@Data
@EqualsAndHashCode
@Entity
public class Product {

    public Product() { }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String specifications;

    @Column
    private String flavor;

    @Column
    private String material;

    @Column
    private String indication;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    @NotNull
    private Manufacturer manufacturer;

    @ManyToOne
    @JoinColumn(name = "product_type_id")
    @NotNull
    private ProductType productType;
}