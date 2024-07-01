package com.epam.service;

import com.epam.domain.Employee;

import java.util.Collection;

/**
 * Adapter Interface
 */
public interface DataReaderAdapter {

    /**
     * This method will read the provided source.
     * The method throw exception if the parameter is empty or null, or the source is not exists,
     *
     * @param source the file with path
     * @return list of Strings where one element is one employee's data.
     * @see Employee
     * @see com.epam.exception.InvalidArgumentException
     */
    Collection<String[]> read(String source);
}
