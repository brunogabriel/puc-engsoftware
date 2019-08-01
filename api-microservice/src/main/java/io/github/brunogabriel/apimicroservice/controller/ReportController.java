package io.github.brunogabriel.apimicroservice.controller;

import io.github.brunogabriel.apimicroservice.domain.Report;
import io.github.brunogabriel.apimicroservice.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by brunogabriel on 2019-07-18.
 */
@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService service;

    @GetMapping("/services/{employee}/{month}")
    public ResponseEntity<List<Report>> findAllByEmployeeAndMonth(@PathVariable String employee,
                                             @PathVariable Integer month) {
        return ResponseEntity.ok(service.findAllByEmployeeAndMonth(employee, month));
    }

    @GetMapping("/services/employees")
    public ResponseEntity<List<String>>findEmployees() {
        return ResponseEntity.ok(service.findEmployees());
    }
}