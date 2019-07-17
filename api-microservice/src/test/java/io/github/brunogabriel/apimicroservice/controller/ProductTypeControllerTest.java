package io.github.brunogabriel.apimicroservice.controller;

import io.github.brunogabriel.apimicroservice.domain.ProductType;
import io.github.brunogabriel.apimicroservice.service.ProductTypeService;
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
public class ProductTypeControllerTest {

    @InjectMocks
    private ProductTypeController controller = new ProductTypeController();

    @Mock
    private ProductTypeService service;

    @Test
    public void shouldFindAll() {
        // given
        List<ProductType> items = Arrays.asList(new ProductType(), new ProductType());
        when(service.findAll()).thenReturn(items);

        // when
        ResponseEntity<List<ProductType>> response = controller.findAll();

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(items, response.getBody());
    }

    @Test
    public void shouldFindById() {
        // given
        Long id = 1999L;
        ProductType item = new ProductType(id, "first", null);
        when(service.findById(id)).thenReturn(Optional.of(item));

        // when
        ResponseEntity<ProductType> response = controller.findById(id);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(item, response.getBody());
    }

    @Test
    public void shouldNotFindById() {
        // given
        Long id = 1999L;
        when(service.findById(id)).thenReturn(Optional.empty());

        // when
        ResponseEntity<ProductType> response = controller.findById(id);

        // then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void shouldSave() {
        // given
        ProductType expected = new ProductType(1L, "saved", null);
        when(service.save(expected)).thenReturn(expected);

        // when
        ResponseEntity<ProductType> response = controller.save(expected);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

    @Test
    public void shouldUpdateById() {
        // given
        Long id = 100L;
        ProductType expected = new ProductType(id, "updated", null);
        when(service.findById(id)).thenReturn(Optional.of(new ProductType()));
        when(service.save(expected)).thenReturn(expected);

        // when
        ResponseEntity<ProductType> response = controller.update(id, expected);

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
        ResponseEntity<ProductType> expected = controller.update(id, new ProductType());

        // then
        assertEquals(HttpStatus.NOT_FOUND, expected.getStatusCode());
    }

    @Test
    public void shouldDeleteById() {
        // when
        Long id = 1999L;
        when(service.findById(id)).thenReturn(Optional.of(new ProductType()));
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
