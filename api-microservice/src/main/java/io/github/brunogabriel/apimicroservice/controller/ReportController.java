package io.github.brunogabriel.apimicroservice.controller;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.brunogabriel.apimicroservice.domain.Report;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by brunogabriel on 2019-07-18.
 */
@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @GetMapping("/services/{employee}/{month}")
    public ResponseEntity<List<Report>> find(@PathVariable String employee,
                                             @PathVariable Integer month) {
        try {
            InputStream inputStream = TypeReference.class.getResourceAsStream("/mock/services_report_mock.json");
            List<Report> reports = new ObjectMapper().readValue(inputStream, new TypeReference<List<Report>>() {});
            List<Report> filteredReport = reports
                    .stream()
                    .filter(it -> it.employee.equalsIgnoreCase(employee))
                    .filter(it -> {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(it.date);
                        return (calendar.get(Calendar.MONTH)+1) == month;
                    })
                    .sorted(Comparator.comparing(Report::getType))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(filteredReport);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/services/employees")
    public ResponseEntity<List<String>>findEmployees() {
        try {
            InputStream inputStream = TypeReference.class.getResourceAsStream("/mock/services_report_mock.json");
            List<Report> reports = new ObjectMapper().readValue(inputStream, new TypeReference<List<Report>>() {});
            return ResponseEntity.ok(reports.stream()
                    .map(report -> report.employee)
                    .sorted()
                    .distinct()
                    .collect(Collectors.toList()));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }
    }
}