package io.github.brunogabriel.apimicroservice.service;

import io.github.brunogabriel.apimicroservice.domain.ProductType;
import io.github.brunogabriel.apimicroservice.repository.ProductTypeRepository;
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
public class ProductTypeServiceTest {

    @InjectMocks
    private ProductTypeService service = new ProductTypeService();

    @Mock
    private ProductTypeRepository repository;

    @Test
    public void shouldFindAll() {
        // given
        List<ProductType> expected = Arrays.asList(
                new ProductType(1L,"first", null),
                new ProductType(2L, "second", null));
        when(repository.findAll()).thenReturn(expected);

        // when
        List<ProductType> result = service.findAll();

        // then
        assertEquals(expected, result);
    }

    @Test
    public void shouldFindById() {
        // given
        Long productTypeId = 1L;
        Optional<ProductType> expected = Optional.of(new ProductType(productTypeId, "", null));
        when(repository.findById(productTypeId)).thenReturn(expected);

        // when
        Optional<ProductType> result = service.findById(productTypeId);

        // then
        assertEquals(expected, result);
    }

    @Test
    public void shouldSave() {
        // given
        ProductType expected = new ProductType(100L, "save", null);
        when(repository.save(expected)).thenReturn(expected);

        // when
        ProductType result = service.save(expected);

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
