package com.epam.service;


import com.epam.domain.Employee;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReportingServiceTest {

    private final DataAnalyzerService dataAnalyzerService = new DataAnalyzerServiceImpl();

    private final ReportingService underTest = new ReportingServiceImpl(dataAnalyzerService);

    @Test
    void generateReport() {
        var ceo = new Employee(1L, "employee", "employee", new BigDecimal("40000"));
        var employees = getEmployeeList(ceo);
        var reports = underTest.generateReport(employees);
        assertEquals(7, reports.size());
        var employee = reports.stream().filter(report -> report.employee().id() == 6L).findFirst();
        assertFalse(employee.isEmpty());
        assertFalse(employee.get().hasMoreThanFourManager());
        assertTrue(employee.get().lessThenRequiredManagerSalary());
        assertFalse(employee.get().moreThanAllowedManagerSalary());

    }

    private Collection<Employee> getEmployeeList(Employee ceo) {
        var employee1 = new Employee(2L, "employee", "employee", new BigDecimal("15974"), 1L);
        var employee2 = new Employee(3L, "employee", "employee", new BigDecimal("19741"), 2L);
        var employee3 = new Employee(4L, "employee", "employee", new BigDecimal("29712"), 3L);
        var employee4 = new Employee(5L, "employee", "employee", new BigDecimal("52200"), 4L);
        var employee5 = new Employee(6L, "employee", "employee", new BigDecimal("3214"), 5L);
        var employee6 = new Employee(7L, "employee", "employee", new BigDecimal("22000"), 6L);
        return List.of(ceo, employee1, employee2, employee3, employee4, employee5, employee6);
    }

}