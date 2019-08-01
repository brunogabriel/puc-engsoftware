package io.github.brunogabriel.apimicroservice.controller;

import io.github.brunogabriel.apimicroservice.domain.PetAssistance;
import io.github.brunogabriel.apimicroservice.service.PetAssistanceService;
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
public class PetAssistanceControllerTest {
    @InjectMocks
    private PetAssistanceController controller = new PetAssistanceController();

    @Mock
    private PetAssistanceService service;

    @Test
    public void shouldFindByPetId() {
        // given
        Long petId = 100L;
        List<PetAssistance> expected = Arrays.asList(
                new PetAssistance(),
                new PetAssistance()
        );
        when(service.findAllByPetId(petId)).thenReturn(expected);

        // when
        ResponseEntity<List<PetAssistance>> response = controller.findAllByPetId(petId);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }
}
