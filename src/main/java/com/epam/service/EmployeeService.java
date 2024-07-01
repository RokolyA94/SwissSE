package com.epam.service;

import com.epam.domain.Employee;

import java.util.Collection;

public interface EmployeeService {

    Collection<Employee> constructEmployeeList(Collection<String[]> rawData);
}
