package io.github.brunogabriel.apimicroservice.controller;

import io.github.brunogabriel.apimicroservice.domain.Product;
import io.github.brunogabriel.apimicroservice.service.ProductService;
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
public class ProductControllerTest {

    @InjectMocks
    private ProductController controller = new ProductController();

    @Mock
    private ProductService service;

    @Test
    public void shouldFindAll() {
        // given
        List<Product> items = Arrays.asList(new Product(), new Product());
        when(service.findAll()).thenReturn(items);

        // when
        ResponseEntity<List<Product>> response = controller.findAll();

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(items, response.getBody());
    }

    @Test
    public void shouldFindById() {
        // given
        Long id = 1999L;
        Product item = new Product();
        when(service.findById(id)).thenReturn(Optional.of(item));

        // when
        ResponseEntity<Product> response = controller.findById(id);

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
        ResponseEntity<Product> response = controller.findById(id);

        // then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void shouldSave() {
        // given
        Product expected = new Product();
        when(service.save(expected)).thenReturn(expected);

        // when
        ResponseEntity<Product> response = controller.save(expected);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

    @Test
    public void shouldUpdateById() {
        // given
        Long id = 100L;
        Product expected = new Product();
        when(service.findById(id)).thenReturn(Optional.of(new Product()));
        when(service.save(expected)).thenReturn(expected);

        // when
        ResponseEntity<Product> response = controller.update(id, expected);

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
        ResponseEntity<Product> expected = controller.update(id, new Product());

        // then
        assertEquals(HttpStatus.NOT_FOUND, expected.getStatusCode());
    }

    @Test
    public void shouldDeleteById() {
        // when
        Long id = 1999L;
        when(service.findById(id)).thenReturn(Optional.of(new Product()));
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
