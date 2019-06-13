package io.github.brunogabriel.apimicroservice.controller;

import io.github.brunogabriel.apimicroservice.domain.Manufacturer;
import io.github.brunogabriel.apimicroservice.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by brunogabriel on 2019-06-12.
 */
@RestController
@RequestMapping("/api/manufacturers")
public class ManufacturerController {

    @Autowired
    private ManufacturerService manufacturerService;

    @GetMapping
    public ResponseEntity<List<Manufacturer>> findAll() {
        return ResponseEntity.ok(manufacturerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manufacturer> findById(@PathVariable Long id) {
        Optional<Manufacturer> manufacturerOptional = manufacturerService.findById(id);
        if (manufacturerOptional.isPresent()) {
            return ResponseEntity.ok(manufacturerOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Manufacturer> save(@Valid @RequestBody Manufacturer manufacturer) {
        return ResponseEntity.ok(manufacturerService.save(manufacturer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Manufacturer> update(@PathVariable Long id, @Valid @RequestBody Manufacturer manufacturer) {
        if (manufacturerService.findById(id).isPresent()) {
            return ResponseEntity.ok(manufacturerService.save(manufacturer));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (manufacturerService.findById(id).isPresent()) {
            manufacturerService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}