package io.github.brunogabriel.apimicroservice.service;

import io.github.brunogabriel.apimicroservice.domain.PetAssistance;
import io.github.brunogabriel.apimicroservice.repository.PetAssistanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetAssistanceService {

    @Autowired
    private PetAssistanceRepository petAssistanceRepository;

    public List<PetAssistance> findAllByPetId(Long petId) {
        return petAssistanceRepository.findAllByPetId(petId);
    }
}
