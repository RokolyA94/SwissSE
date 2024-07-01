package com.epam.domain;

import java.math.BigDecimal;

public record ReportDto(
        Employee employee,
        boolean hasMoreThanFourManager,
        int managerCount,
        boolean lessThenRequiredManagerSalary,
        boolean moreThanAllowedManagerSalary,
        BigDecimal salaryCorrection
) {
}
