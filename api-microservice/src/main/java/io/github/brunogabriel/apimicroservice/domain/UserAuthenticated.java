package io.github.brunogabriel.apimicroservice.domain;

import lombok.Data;

/**
 * Created by brunogabriel on 2019-06-15.
 */
@Data
public class UserAuthenticated {
    public String username;
    public String token;
}
