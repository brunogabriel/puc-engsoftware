package io.github.brunogabriel.apimicroservice.controller;

import io.github.brunogabriel.apimicroservice.domain.Manufacturer;
import io.github.brunogabriel.apimicroservice.service.ManufacturerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by brunogabriel on 2019-07-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ManufacturerControllerTest {

    @InjectMocks
    private ManufacturerController controller = new ManufacturerController();

    @Mock
    private ManufacturerService service;

    @Test
    public void shouldFindAll() {
        // given
        List<Manufacturer> manufacturers = Arrays.asList(new Manufacturer(), new Manufacturer());
        when(service.findAll()).thenReturn(manufacturers);

        // when
        ResponseEntity<List<Manufacturer>> response = controller.findAll();

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(manufacturers, response.getBody());
    }

    @Test
    public void shouldFindById() {
        // given
        Long id = 1999L;
        Manufacturer manufacturer = new Manufacturer(id, "first", null);
        when(service.findById(id)).thenReturn(Optional.of(manufacturer));

        // when
        ResponseEntity<Manufacturer> response = controller.findById(id);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(manufacturer, response.getBody());
    }

    @Test
    public void shouldNotFindById() {
        // given
        Long id = 1999L;
        when(service.findById(id)).thenReturn(Optional.empty());

        // when
        ResponseEntity<Manufacturer> response = controller.findById(id);

        // then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void shouldSave() {
        // given
        Manufacturer expected = new Manufacturer(1L, "saved", null);
        when(service.save(expected)).thenReturn(expected);

        // when
        ResponseEntity<Manufacturer> response = controller.save(expected);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

    @Test
    public void shouldUpdateById() {
        // given
        Long id = 100L;
        Manufacturer expected = new Manufacturer(id, "updated", null);
        when(service.findById(id)).thenReturn(Optional.of(new Manufacturer()));
        when(service.save(expected)).thenReturn(expected);

        // when
        ResponseEntity<Manufacturer> response = controller.update(id, expected);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

    @Test
    public void shouldNotUpdateById() {
        // given
        Long id = 1999L;
        when(service.findById(any())).thenReturn(Optional.empty());

        // when
        ResponseEntity<Manufacturer> expected = controller.update(id, new Manufacturer());

        // then
        assertEquals(HttpStatus.NOT_FOUND, expected.getStatusCode());
    }

    @Test
    public void shouldDeleteById() {
        // when
        Long id = 1999L;
        when(service.findById(id)).thenReturn(Optional.of(new Manufacturer()));
        ResponseEntity response = controller.delete(id);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(service, times(1)).deleteById(id);
    }

    @Test
    public void shouldNotDeleteById() {
        // when
        Long id = 1999L;
        when(service.findById(id)).thenReturn(Optional.empty());
        ResponseEntity response = controller.delete(id);

        // then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(service, never()).deleteById(id);
    }
}
