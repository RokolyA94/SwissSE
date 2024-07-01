package com.epam.service;

import com.epam.Utils;
import com.epam.exception.InvalidArgumentException;
import com.epam.exception.InvalidCsv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CsvDataReaderAdapterImpl implements DataReaderAdapter {

    private static final String DELIMITER = ",";

    private static final int MAXIMUM_EMPLOYEE_NUMBER = 1000;

    @Override
    public Collection<String[]> read(String source) {
        if (Utils.isEmpty(source)) {
            throw new InvalidArgumentException("File name cannot be blank");
        }
        List<String[]> result = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(source))) {
            String line;
            var lineNumber = -1;
            while ((line = bufferedReader.readLine()) != null) {
                if (result.size() == MAXIMUM_EMPLOYEE_NUMBER) {
                    throw new InvalidCsv("The file contains more then 1000 employee");
                }
                lineNumber++;
                // first line is the header
                if (lineNumber == 0) {
                    continue;
                }
                var rawData = line.split(DELIMITER);
                result.add(rawData);
            }
        } catch (FileNotFoundException e) {
            throw new InvalidArgumentException("File not found", e);
        } catch (IOException e) {
            // TODO [irokolya]: handle exception properly
            throw new RuntimeException(e);
        }
        return result;
    }
}
