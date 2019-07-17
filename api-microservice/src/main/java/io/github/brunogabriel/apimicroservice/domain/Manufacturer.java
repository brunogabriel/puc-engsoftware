package io.github.brunogabriel.apimicroservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

/**
 * Created by brunogabriel on 2019-06-12.
 */
@Data
@EqualsAndHashCode
@Entity
public class Manufacturer {

    public Manufacturer() { }

    public Manufacturer(Long id, String name, List<Product> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "manufacturer",
            targetEntity = Product.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Product> products;
}
