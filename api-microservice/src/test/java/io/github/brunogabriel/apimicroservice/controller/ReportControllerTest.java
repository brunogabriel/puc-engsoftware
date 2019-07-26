package io.github.brunogabriel.apimicroservice.controller;

import io.github.brunogabriel.apimicroservice.domain.Report;
import io.github.brunogabriel.apimicroservice.service.ReportService;
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
public class ReportControllerTest {
    @InjectMocks
    private ReportController reportController = new ReportController();
    @Mock
    private ReportService reportService;

    @Test
    public void shouldFindEmployees() {
        // given
        List<String> expected = Arrays.asList("A", "B");
        when(reportService.findEmployees()).thenReturn(expected);

        // when
        ResponseEntity<List<String>> response = reportController.findEmployees();

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

    @Test
    public void should() {
        // given
        String employee = "anyEmployee";
        int month = 12;
        List<Report> expected = Arrays.asList(new Report(),
                new Report());
        when(reportService.findAllByEmployeeAndMonth(employee, month)).thenReturn(expected);

        // when
        ResponseEntity<List<Report>> response = reportController
                .findAllByEmployeeAndMonth(employee, month);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }
}
