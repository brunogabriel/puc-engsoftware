package io.github.brunogabriel.apimicroservice.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.brunogabriel.apimicroservice.domain.Service;
import io.github.brunogabriel.apimicroservice.utils.ApplicationUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by brunogabriel on 2019-06-19.
 */
@RestController
@RequestMapping("/api/services")
public class ServiceController {
    @GetMapping("/{petId}")
    public ResponseEntity<List<Service>> findAll(@PathVariable Long petId) {
        try {
            InputStream inputStream = TypeReference.class.getResourceAsStream("/mock/services_mock.json");
            List<Service> services = new ObjectMapper().readValue(inputStream, new TypeReference<List<Service>>() {});
            List<Service> filteredServices = services.stream()
                    .filter(it -> it.petId.equals(petId))
                    .collect(Collectors.toList());
            ApplicationUtils.simulateTimeout();
            return ResponseEntity.ok(filteredServices);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }
    }
}
