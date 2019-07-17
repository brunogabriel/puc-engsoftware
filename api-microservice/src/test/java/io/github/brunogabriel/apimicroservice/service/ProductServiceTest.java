package io.github.brunogabriel.apimicroservice.service;

import io.github.brunogabriel.apimicroservice.domain.Product;
import io.github.brunogabriel.apimicroservice.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by brunogabriel on 2019-07-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService service = new ProductService();

    @Mock
    private ProductRepository repository;

    @Test
    public void shouldFindAll() {
        // given
        List<Product> expected = Arrays.asList(
                new Product(),
                new Product());
        when(repository.findAll()).thenReturn(expected);

        // when
        List<Product> result = service.findAll();

        // then
        assertEquals(expected, result);
    }

    @Test
    public void shouldFindById() {
        // given
        Long productId = 1L;
        Optional<Product> expected = Optional.of(new Product());
        when(repository.findById(productId)).thenReturn(expected);

        // when
        Optional<Product> result = service.findById(productId);

        // then
        assertEquals(expected, result);
    }

    @Test
    public void shouldSave() {
        // given
        Product expected = new Product();
        when(repository.save(expected)).thenReturn(expected);

        // when
        Product result = service.save(expected);

        // then
        assertEquals(expected, result);
    }

    @Test
    public void shouldDeleteById() {
        // given
        Long id = 999L;

        // when
        service.deleteById(id);

        // then
        verify(repository, times(1)).deleteById(id);
    }
}
