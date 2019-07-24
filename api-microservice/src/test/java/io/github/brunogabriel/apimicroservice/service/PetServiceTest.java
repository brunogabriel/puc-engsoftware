package io.github.brunogabriel.apimicroservice.service;

import io.github.brunogabriel.apimicroservice.domain.Pet;
import io.github.brunogabriel.apimicroservice.repository.PetRepository;
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
public class PetServiceTest {
    @InjectMocks
    private PetService petService = new PetService();

    @Mock
    private PetRepository petRepository;

    @Test
    public void shouldFindAllByClientId() {
        // given
        String clientId = "anyClientId";
        List<Pet> expected = Arrays.asList(new Pet(), new Pet(), new Pet());
        when(petRepository.findAllByClientId(clientId)).thenReturn(expected);

        // when
        List<Pet> result = petService.findAllByClientId(clientId);

        // then
        assertEquals(expected, result);
    }
}
