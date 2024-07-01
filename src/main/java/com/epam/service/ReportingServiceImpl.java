package com.epam.service;

import com.epam.domain.Employee;
import com.epam.domain.ReportDto;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ReportingServiceImpl implements ReportingService {

    private static final int MAXIMUM_MANAGER_CHAIN_SIZE = 4;

    private final DataAnalyzerService dataAnalyzerService;

    public ReportingServiceImpl(DataAnalyzerService dataAnalyzerService) {
        this.dataAnalyzerService = dataAnalyzerService;
    }

    @Override
    public Collection<ReportDto> generateReport(Collection<Employee> employees) {
        var averageSalary = dataAnalyzerService.getAverageSalary(employees);
        var maximumAllowedManagerSalary = dataAnalyzerService.getMaximumAllowedManagerSalary(averageSalary);
        var minimumAllowedManagerSalary = dataAnalyzerService.getMinimumAllowedManagerSalary(averageSalary);
        var managersMap = getManagersMap(employees);
        var sortedEmployees = dataAnalyzerService.sortListByManagerId(employees);

        return sortedEmployees.stream()
                .map(employee -> {
                    boolean lessThenRequiredManagerSalary = employee.salary().compareTo(minimumAllowedManagerSalary) < 0;
                    boolean moreThanAllowedManagerSalary = employee.salary().compareTo(maximumAllowedManagerSalary) > 0;
                    var managerChainCount = dataAnalyzerService.getManagerChainCount(managersMap, employee.managerId(), 0);
                    if (lessThenRequiredManagerSalary || moreThanAllowedManagerSalary || managerChainCount > MAXIMUM_MANAGER_CHAIN_SIZE) {
                        return new ReportDto(employee,
                                MAXIMUM_MANAGER_CHAIN_SIZE < managerChainCount,
                                managerChainCount,
                                lessThenRequiredManagerSalary,
                                moreThanAllowedManagerSalary,
                                dataAnalyzerService.calculateManagerSalaryCorrection(minimumAllowedManagerSalary,
                                        maximumAllowedManagerSalary, employee.salary())
                        );
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .toList();
    }

    private Map<Long, Long> getManagersMap(Collection<Employee> employees) {
        return employees.stream()
                .filter(employee -> !employee.isCeo())
                .collect(Collectors.toMap(Employee::id, Employee::managerId));
    }
}
