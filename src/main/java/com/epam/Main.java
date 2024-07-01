package com.epam;

import com.epam.exception.InvalidArgumentException;
import com.epam.service.DataAnalyzerServiceImpl;
import com.epam.service.EmployeeServiceImpl;
import com.epam.service.CsvDataReaderAdapterImpl;
import com.epam.service.ReportingServiceImpl;

public class Main {

    public static void main(String[] args) {

        if (null == args || args.length == 0) {
            throw new InvalidArgumentException("No parameters given");
        }
        var fileReaderService = new CsvDataReaderAdapterImpl();
        var employeesRawData = fileReaderService.read(args[0]);
        if (Utils.isEmpty(employeesRawData)) {
            throw new IllegalStateException("File does not contain employees data");
        }
        var employeeService = new EmployeeServiceImpl();
        var employees = employeeService.constructEmployeeList(employeesRawData);
        var dataAnalyzerService = new DataAnalyzerServiceImpl();
        var reportingService = new ReportingServiceImpl(dataAnalyzerService);
        var report = reportingService.generateReport(employees);
        report.forEach(System.out::println);
    }
}