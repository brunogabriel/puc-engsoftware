package io.github.brunogabriel.apimicroservice.domain;

import lombok.Data;

/**
 * Created by brunogabriel on 2019-06-13.
 */
@Data
public class UserCredentials {
    public String username;
    public String password;
}