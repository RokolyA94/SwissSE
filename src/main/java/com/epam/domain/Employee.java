package com.epam.domain;

import java.math.BigDecimal;

public record Employee(
        Long id,
        String firstName,
        String lastName,
        BigDecimal salary,
        Long managerId
) {
    public Employee(long id, String firstName, String lastName, BigDecimal salary) {
        this(id, firstName, lastName, salary, null);
    }

    public boolean isCeo() {
        return managerId == null;
    }
}
