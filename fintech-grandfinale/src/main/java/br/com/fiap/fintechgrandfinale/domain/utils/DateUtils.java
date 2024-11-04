package br.com.fiap.fintechgrandfinale.domain.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {
    // Default date format
    private static final String DEFAULT_FORMAT = "dd/MM/yyyy";

    // Method to convert a date string to LocalDate
    public static LocalDate parseDate(String dateString) {
        return parseDate(dateString, DEFAULT_FORMAT);
    }

    // Method to convert a date string to LocalDate with custom format
    public static LocalDate parseDate(String dateString, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        try {
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Error parsing date: " + dateString + ". Expected format: " + format, e);
        }
    }
}
