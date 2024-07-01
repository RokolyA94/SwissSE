package com.epam.service;

import com.epam.domain.Employee;
import com.epam.domain.ReportDto;

import java.util.Collection;

public interface ReportingService {

    Collection<ReportDto> generateReport(Collection<Employee> employees);

}
