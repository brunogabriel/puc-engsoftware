package io.github.brunogabriel.apimicroservice.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.brunogabriel.apimicroservice.domain.Pet;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * Created by brunogabriel on 2019-06-17.
 */
@RestController
@RequestMapping("/api/pets")
public class PetController {

    private void simulateTimeout() {
        int time = ThreadLocalRandom.current().nextInt(0, 5);
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) { }
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<List<Pet>> findAll(@PathVariable String clientId) {
        try {
            InputStream inputStream = TypeReference.class.getResourceAsStream("/mock/pets_mock.json");
            List<Pet> pets = new ObjectMapper().readValue(inputStream, new TypeReference<List<Pet>>(){});
            List<Pet> filteredPets = pets.stream()
                    .filter(it -> it.clientId.equals(clientId))
                    .collect(Collectors.toList());
            simulateTimeout(); // simulate timeout
            return ResponseEntity.ok(filteredPets);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }
    }
}

