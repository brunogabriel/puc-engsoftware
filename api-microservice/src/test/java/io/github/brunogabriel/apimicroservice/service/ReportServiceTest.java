package io.github.brunogabriel.apimicroservice.service;

import io.github.brunogabriel.apimicroservice.domain.Report;
import io.github.brunogabriel.apimicroservice.repository.ReportRepository;
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
public class ReportServiceTest {
    @InjectMocks
    private ReportService reportService = new ReportService();

    @Mock
    private ReportRepository reportRepository;

    @Test
    public void shouldFindEmployees() {
        // given
        List<String> expected = Arrays.asList("A", "B", "C");
        when(reportRepository.findEmployees()).thenReturn(expected);

        // when
        List<String> result = reportService.findEmployees();

        // then
        assertEquals(expected, result);
    }

    @Test
    public void shouldFindAll() {
        // given
        String employee = "anyEmployee";
        int month = 12;
        List<Report> expected = Arrays.asList(
                new Report(),
                new Report()
        );
        when(reportRepository.findAllByEmployeeAndMonth(employee, month)).thenReturn(expected);

        // when
        List<Report> result = reportService.findAllByEmployeeAndMonth(employee, month);

        // then
        assertEquals(expected, result);
    }
}
