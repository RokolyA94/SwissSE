package com.epam.service;

import com.epam.domain.Employee;

import java.math.BigDecimal;
import java.util.Collection;

public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public Collection<Employee> constructEmployeeList(Collection<String[]> rawData) {
        return rawData.stream()
                .map(data -> {
                    if (data.length == 4) { // CEO
                        return new Employee(Long.parseLong(data[0]), data[1], data[2], new BigDecimal(data[3]));

                    }
                    return new Employee(Long.parseLong(data[0]), data[1], data[2], new BigDecimal(data[3]),
                            Long.parseLong(data[4]));
                }).toList();
    }
}
