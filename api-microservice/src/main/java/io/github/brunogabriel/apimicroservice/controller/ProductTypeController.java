package io.github.brunogabriel.apimicroservice.controller;

import io.github.brunogabriel.apimicroservice.domain.ProductType;
import io.github.brunogabriel.apimicroservice.service.ProductTypeService;
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
@RequestMapping("/api/productTypes")
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    @GetMapping
    public ResponseEntity<List<ProductType>> findAll() {
        return ResponseEntity.ok(productTypeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductType> findById(@PathVariable Long id) {
        Optional<ProductType> productTypeOptional = productTypeService.findById(id);
        if (productTypeOptional.isPresent()) {
            return ResponseEntity.ok(productTypeOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ProductType> save(@Valid @RequestBody ProductType productType) {
        return ResponseEntity.ok(productTypeService.save(productType));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductType> update(@PathVariable Long id, @Valid @RequestBody ProductType productType) {
        if (productTypeService.findById(id).isPresent()) {
            return ResponseEntity.ok(productTypeService.save(productType));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (productTypeService.findById(id).isPresent()) {
            productTypeService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}