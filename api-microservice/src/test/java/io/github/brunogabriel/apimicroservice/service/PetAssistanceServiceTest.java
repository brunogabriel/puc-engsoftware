package io.github.brunogabriel.apimicroservice.service;

import io.github.brunogabriel.apimicroservice.domain.PetAssistance;
import io.github.brunogabriel.apimicroservice.repository.PetAssistanceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class PetAssistanceServiceTest {

    @InjectMocks
    private PetAssistanceService petAssistanceService = new PetAssistanceService();

    @Mock
    private PetAssistanceRepository petAssistanceRepository;

    @Test
    public void shouldFindAllByPetId() {
        // given
        Long petId = 100L;
        List<PetAssistance> expected = Arrays.asList(
                new PetAssistance(),
                new PetAssistance()
        );
        when(petAssistanceRepository.findAllByPetId(petId)).thenReturn(expected);

        // when
        List<PetAssistance> result = petAssistanceService.findAllByPetId(petId);

        // then
        assertEquals(expected, result);
    }
}
