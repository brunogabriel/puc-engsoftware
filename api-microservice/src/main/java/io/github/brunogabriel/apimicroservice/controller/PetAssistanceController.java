package io.github.brunogabriel.apimicroservice.controller;

import io.github.brunogabriel.apimicroservice.domain.PetAssistance;
import io.github.brunogabriel.apimicroservice.service.PetAssistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by brunogabriel on 2019-06-19.
 */
@RestController
@RequestMapping("/api/services")
public class PetAssistanceController {
    @Autowired
    private PetAssistanceService petAssistanceService;

    @GetMapping("/{petId}")
    public ResponseEntity<List<PetAssistance>> findAllByPetId(@PathVariable Long petId) {
        return ResponseEntity.ok(petAssistanceService.findAllByPetId(petId));
    }
}
