package io.github.brunogabriel.apimicroservice.service;

import io.github.brunogabriel.apimicroservice.domain.Report;
import io.github.brunogabriel.apimicroservice.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public List<Report> findAllByEmployeeAndMonth(String employee, Integer month) {
        return reportRepository.findAllByEmployeeAndMonth(employee, month);
    }

    public List<String> findEmployees() {
        return reportRepository.findEmployees();
    }
}
