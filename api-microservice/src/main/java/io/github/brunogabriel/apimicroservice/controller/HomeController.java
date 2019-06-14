package io.github.brunogabriel.apimicroservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by brunogabriel on 2019-06-13.
 */

//TODO: remove in future only to test
@RestController
public class HomeController {
    @GetMapping("/home")
    public String getHome() {
        return "Welcome, Home!";
    }
}