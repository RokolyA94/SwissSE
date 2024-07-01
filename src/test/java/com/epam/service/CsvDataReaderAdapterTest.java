package com.epam.service;

import com.epam.exception.InvalidArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CsvDataReaderAdapterTest {

    private final DataReaderAdapter underTest = new CsvDataReaderAdapterImpl();

    @Test
    void readFile_No__AndConstructEmployee_provided() {
        assertThrows(InvalidArgumentException.class, () -> underTest.read(null));
    }

    @Test
    void readFile__AndConstructEmployee_not_exists() {
        assertThrows(InvalidArgumentException.class, () -> underTest.read("test.txt"));
    }

    @Test
    void readFile__AndConstructEmployee_exists() {
        underTest.read("src/test/resources/valid_test_input.csv");
    }

    @Test
    void read_AndConstructEmployee_Success() {
        var result = underTest.read("src/test/resources/valid_test_input.csv");
        assertNotNull(result);
        assertEquals(7, result.size());
    }
}