package com.epam.service;

import com.epam.domain.Employee;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class DataAnalyzerServiceTest {

    private final DataAnalyzerService underTest = new DataAnalyzerServiceImpl();

    @Test
    void calculateManagerSalaryCorrection_multiplePath() {
        var minimumSalary = new BigDecimal("10000");
        var maximumSalary = new BigDecimal("20000");
        var currentSalary = new BigDecimal("2400");
        var result = underTest.calculateManagerSalaryCorrection(minimumSalary, maximumSalary, currentSalary);
        //less than allowed
        assertEquals(new BigDecimal("7600"), result);
        // more than allowed
        currentSalary = new BigDecimal("22400");
        result = underTest.calculateManagerSalaryCorrection(minimumSalary, maximumSalary, currentSalary);
        assertEquals(new BigDecimal("-2400"), result);
        // equals with minimum
        currentSalary = new BigDecimal("10000");
        result = underTest.calculateManagerSalaryCorrection(minimumSalary, maximumSalary, currentSalary);
        assertEquals(BigDecimal.ZERO, result);
        // equals with maximum
        currentSalary = new BigDecimal("20000");
        result = underTest.calculateManagerSalaryCorrection(minimumSalary, maximumSalary, currentSalary);
        assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    void getManagerChainCount_OnlyCeo() {
        var ceo = new Employee(1, "ceo", "ceo", new BigDecimal("10000"));
        int managerChainCount = underTest.getManagerChainCount(getManagersMap(List.of(ceo)), null, 0);
        assertEquals(0, managerChainCount);
    }

    @Test
    void getManagerChainCount_MoreManager() {
        var employee = new Employee(1L, "employee", "employee", new BigDecimal("10000"));
        var employee1 = new Employee(2L, "employee", "employee", new BigDecimal("10000"), 1L);
        var employee2 = new Employee(3L, "employee", "employee", new BigDecimal("10000"), 2L);
        var employee3 = new Employee(4L, "employee", "employee", new BigDecimal("10000"), 3L);
        var employee4 = new Employee(5L, "employee", "employee", new BigDecimal("10000"), 4L);
        var employee5 = new Employee(6L, "employee", "employee", new BigDecimal("10000"), 5L);
        var employee6 = new Employee(7L, "employee", "employee", new BigDecimal("10000"), 6L);
        var employees = List.of(employee, employee1, employee2, employee3, employee4, employee5, employee6);
        Map<Long, Long> managersMap = getManagersMap(employees);

        int managerChainCount = underTest.getManagerChainCount(managersMap, employee6.managerId(), 0);
        assertEquals(5, managerChainCount);
    }

    private Map<Long, Long> getManagersMap(Collection<Employee> employees) {
        return employees.stream()
                .filter(employee -> !employee.isCeo())
                .collect(Collectors.toMap(Employee::id, Employee::managerId));
    }

}