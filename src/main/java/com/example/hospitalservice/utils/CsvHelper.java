package com.example.hospitalservice.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import com.example.hospitalservice.entities.Patient;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class CsvHelper {
    public enum Header {
        Name, Age, Created_at, Updated_at
    }

    public static ByteArrayInputStream dataToCSV(Patient patient) {
        final CSVFormat format = CSVFormat.DEFAULT.withHeader(Header.class);
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
                CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
            List<Object> data = Arrays.asList(
                    patient.getName(),
                    String.valueOf(patient.getAge()),
                    String.valueOf(patient.getCreated_at()),
                    String.valueOf(patient.getUpdated_at()));

            csvPrinter.printRecord(data);
            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }
}
