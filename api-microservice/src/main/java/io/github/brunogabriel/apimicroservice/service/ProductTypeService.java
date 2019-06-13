package io.github.brunogabriel.apimicroservice.service;

import io.github.brunogabriel.apimicroservice.domain.ProductType;
import io.github.brunogabriel.apimicroservice.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by brunogabriel on 2019-06-12.
 */
@Service
public class ProductTypeService {

    @Autowired
    private ProductTypeRepository productTypeRepository;

    public List<ProductType> findAll() {
        return productTypeRepository.findAll();
    }

    public Optional<ProductType> findById(Long id) {
        return productTypeRepository.findById(id);
    }

    public ProductType save(ProductType manufacturer) {
        return productTypeRepository.save(manufacturer);
    }

    public void deleteById(Long id) {
        productTypeRepository.deleteById(id);
    }
}