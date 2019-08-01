package io.github.brunogabriel.apimicroservice.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.brunogabriel.apimicroservice.domain.Report;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ReportRepository {

    private static final String MOCK_RESOURCE_PATH = "/mock/services_report_mock.json";

    public List<Report> findAllByEmployeeAndMonth(String employee, Integer month) {
        try {
            InputStream inputStream = TypeReference.class.getResourceAsStream(MOCK_RESOURCE_PATH);
            List<Report> reports = new ObjectMapper().readValue(inputStream, new TypeReference<List<Report>>() {});
            return reports
                    .stream()
                    .filter(it -> it.employee.equalsIgnoreCase(employee))
                    .filter(it -> {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(it.date);
                        return (calendar.get(Calendar.MONTH)+1) == month;
                    })
                    .sorted(Comparator.comparing(Report::getType))
                    .collect(Collectors.toList());
        } catch (Exception exception) {
            return Collections.emptyList();
        }
    }

    public List<String> findEmployees() {
        try {
            InputStream inputStream = TypeReference.class.getResourceAsStream(MOCK_RESOURCE_PATH);
            List<Report> reports = new ObjectMapper().readValue(inputStream, new TypeReference<List<Report>>() {});
            return reports.stream()
                    .map(report -> report.employee)
                    .sorted()
                    .distinct()
                    .collect(Collectors.toList());
        } catch (Exception exception) {
            return Collections.emptyList();
        }
    }
}
