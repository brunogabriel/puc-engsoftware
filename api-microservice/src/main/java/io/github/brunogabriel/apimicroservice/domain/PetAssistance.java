package io.github.brunogabriel.apimicroservice.domain;

import lombok.Data;

import java.util.List;

/**
 * Created by brunogabriel on 2019-06-18.
 */
@Data
public class PetAssistance {
    public Long id;
    public Long petId;
    public String type;
    public String duration;
    public String date;
    public double price;
    public String employee;
    public List<String> products;
    public List<String> annotations;
}