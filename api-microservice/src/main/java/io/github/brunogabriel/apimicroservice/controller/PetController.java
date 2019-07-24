package io.github.brunogabriel.apimicroservice.controller;

import io.github.brunogabriel.apimicroservice.domain.Pet;
import io.github.brunogabriel.apimicroservice.service.PetService;
import io.github.brunogabriel.apimicroservice.utils.ApplicationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by brunogabriel on 2019-06-17.
 */
@RestController
@RequestMapping("/api/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping("/{clientId}")
    public ResponseEntity<List<Pet>> findAll(@PathVariable String clientId) {
        ApplicationUtils.simulateTimeout();
        return ResponseEntity.ok(petService.findAllByClientId(clientId));
    }
}

