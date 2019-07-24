package io.github.brunogabriel.apimicroservice.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.brunogabriel.apimicroservice.domain.PetAssistance;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PetAssistanceRepository {

    private static final String MOCK_RESOURCE_PATH = "/mock/services_mock.json";

    public List<PetAssistance> findAllByPetId(Long petId) {
        try {
            InputStream inputStream = TypeReference.class.getResourceAsStream(MOCK_RESOURCE_PATH);
            List<PetAssistance> services = new ObjectMapper().readValue(inputStream, new TypeReference<List<PetAssistance>>() {});
            return services.stream()
                    .filter(it -> it.petId.equals(petId))
                    .collect(Collectors.toList());
        } catch (Exception exception) {
            return Collections.emptyList();
        }
    }
}
