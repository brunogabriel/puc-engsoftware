package io.github.brunogabriel.apimicroservice.repository;

import io.github.brunogabriel.apimicroservice.domain.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by brunogabriel on 2019-06-12.
 */
@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
}
