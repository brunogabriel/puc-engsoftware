package io.github.brunogabriel.apimicroservice.controller;

import io.github.brunogabriel.apimicroservice.domain.Pet;
import io.github.brunogabriel.apimicroservice.service.PetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class PetControllerTest {
    @InjectMocks
    private PetController petController = new PetController();

    @Mock
    private PetService petService;

    @Test
    public void shouldFindAll() {
        // given
        String clientId = "anyClientId";
        List<Pet> expected = Arrays.asList(
                new Pet(),
                new Pet(),
                new Pet()
        );
        when(petService.findAllByClientId(clientId)).thenReturn(expected);

        // when
        ResponseEntity<List<Pet>> response = petController.findAll(clientId);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }
}
