package com.epam.service;

import com.epam.domain.Employee;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

public interface DataAnalyzerService {

    /**
     * This method calculate the average salary of the list. to calculate that value, it using the
     * <code> Employee#getSalary</code> value, with the size of the list
     * @param employees List of the company's employees
     * @return Big decimal number which is the average of the salary, with round half UP.
     * @see Employee
     * @see BigDecimal
     * @see java.math.RoundingMode
     */
    BigDecimal getAverageSalary(Collection<Employee> employees);

    @Deprecated
    BigDecimal getMaximumAllowedManagerSalary(Collection<Employee> employees);

    BigDecimal getMaximumAllowedManagerSalary(BigDecimal averageSalary);

    @Deprecated
    BigDecimal getMinimumAllowedManagerSalary(Collection<Employee> employees);

    BigDecimal getMinimumAllowedManagerSalary(BigDecimal averageSalary);

    Collection<Employee> sortListByManagerId(Collection<Employee> employees);

    BigDecimal calculateManagerSalaryCorrection(BigDecimal minimumManagerSalary, BigDecimal maximumManagerSalary,
                                                BigDecimal currentManagerSalary);

    /**
     * This method goes checking is the managerId has a manager inside it.
     * The method use recursion to figure out how many managers are linked to the provided managerId.
     * Recursion Exit condition:
     * <ol>
     *     <li>Recursion reached the managerId is null state (CEO reached)</li>
     *     <li>Manager not found, return the current state</li>
     * </ol>
     *
     * @param managersMap it contains linking of the managers by id. The key is the current managerId,
     *                    the Value is the linked (following) managerId
     * @param managerId   the starting point of the managerId
     * @param count       the current titration number, it should start with 0
     * @return true, if the manager chain is longer then 4. otherwise false
     * @see Employee
     */
    int getManagerChainCount(Map<Long, Long> managersMap, Long managerId, int count);

    /**
     * Filter out the CEO of the Company
     * @param employees list of employees of the Company
     * @return The CEO employee
     * @see Employee
     */
    Employee getCeo(Collection<Employee> employees);
}
