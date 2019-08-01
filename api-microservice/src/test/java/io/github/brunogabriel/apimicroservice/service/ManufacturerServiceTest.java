package io.github.brunogabriel.apimicroservice.service;

import io.github.brunogabriel.apimicroservice.domain.Manufacturer;
import io.github.brunogabriel.apimicroservice.repository.ManufacturerRepository;
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
public class ManufacturerServiceTest {

    @InjectMocks
    private ManufacturerService service = new ManufacturerService();

    @Mock
    private ManufacturerRepository repository;

    @Test
    public void shouldFindAll() {
        // given
        List<Manufacturer> expected = Arrays.asList(
                new Manufacturer(1L,"first", null),
                new Manufacturer(2L, "second", null));
        when(repository.findAll()).thenReturn(expected);

        // when
        List<Manufacturer> result = service.findAll();

        // then
        assertEquals(expected, result);
    }

    @Test
    public void shouldFindById() {
        // given
        Long manufacturerId = 1L;
        Optional<Manufacturer> expected = Optional.of(new Manufacturer(manufacturerId, "", null));
        when(repository.findById(manufacturerId)).thenReturn(expected);

        // when
        Optional<Manufacturer> result = service.findById(manufacturerId);

        // then
        assertEquals(expected, result);
    }

    @Test
    public void shouldSave() {
        // given
        Manufacturer expected = new Manufacturer(100L, "save", null);
        when(repository.save(expected)).thenReturn(expected);

        // when
        Manufacturer result = service.save(expected);

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
