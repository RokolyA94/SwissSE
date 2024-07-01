package com.epam.service;

import com.epam.Utils;
import com.epam.domain.Employee;
import com.epam.exception.CeoNotFound;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class DataAnalyzerServiceImpl implements DataAnalyzerService {

    private static final BigDecimal MINIMUM_MANAGER_PERCENTAGE = new BigDecimal("0.2");

    private static final BigDecimal MAXIMUM_MANAGER_PERCENTAGE = new BigDecimal("0.5");

    @Override
    public BigDecimal getAverageSalary(Collection<Employee> employees) {
        if (Utils.isEmpty(employees)) {
            return BigDecimal.ZERO;
        }
        var sum = employees.stream()
                .map(Employee::salary)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return sum.divide(new BigDecimal(employees.size()), RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal getMaximumAllowedManagerSalary(Collection<Employee> employees) {
        return getAverageSalary(employees).multiply(MAXIMUM_MANAGER_PERCENTAGE);
    }

    @Override
    public BigDecimal getMaximumAllowedManagerSalary(BigDecimal averageSalary) {
        return averageSalary.multiply(MAXIMUM_MANAGER_PERCENTAGE);
    }

    @Override
    public BigDecimal getMinimumAllowedManagerSalary(Collection<Employee> employees) {
        return getAverageSalary(employees).multiply(MINIMUM_MANAGER_PERCENTAGE);
    }

    @Override
    public BigDecimal getMinimumAllowedManagerSalary(BigDecimal averageSalary) {
        return averageSalary.multiply(MINIMUM_MANAGER_PERCENTAGE);
    }

    @Override
    public Collection<Employee> sortListByManagerId(Collection<Employee> employees) {
        var result = employees.stream()
                // TODO [irokolya]: handle the null value inside the Employee, avoid re adding CEO
                .filter(employee -> !employee.isCeo())
                .sorted(Comparator.comparing(Employee::managerId))
                .collect(Collectors.toCollection(ArrayList::new));
        result.addFirst(getCeo(employees));
        return result;
    }

    // Unhandled: multiple CEO
    @Override
    public Employee getCeo(Collection<Employee> employees) {
        return employees.stream()
                .filter(Employee::isCeo)
                .findFirst()
                .orElseThrow(() -> new CeoNotFound("Ceo not found in the Employee list"));
    }

    @Override
    public BigDecimal calculateManagerSalaryCorrection(BigDecimal minimumManagerSalary, BigDecimal maximumManagerSalary,
                                                       BigDecimal currentManagerSalary) {
        // more than allowed
        if (currentManagerSalary.compareTo(maximumManagerSalary) >= 0) {
            return maximumManagerSalary.subtract(currentManagerSalary);
        }
        // less than allowed
        return currentManagerSalary.subtract(minimumManagerSalary).abs();
    }


    @Override
    public int getManagerChainCount(Map<Long, Long> managersMap, Long managerId, int count) {
        // no managers provided
        if (Utils.isEmpty(managersMap)) {
            return 0;
        }
        // reached the CEO
        if (managerId == null) {
            return count;
        }
        // no more manager found, recursion end.
        if (!managersMap.containsKey(managerId)) {
            return count;
        }
        // call recursion
        count++;
        return getManagerChainCount(managersMap, managersMap.get(managerId), count);
    }
}
