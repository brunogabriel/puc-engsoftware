package io.github.brunogabriel.apimicroservice.domain;

import lombok.Data;

import java.util.List;

/**
 * Created by brunogabriel on 2019-06-17.
 */
@Data
public class Pet {
    public Long id;
    public String clientId;
    public String type;
    public String name;
    public String gait;
    public List<String> allergies;
    public List<String> habits;
    public List<String> preferences;
    public List<String> images;
    public int quantityOfServices;
}
