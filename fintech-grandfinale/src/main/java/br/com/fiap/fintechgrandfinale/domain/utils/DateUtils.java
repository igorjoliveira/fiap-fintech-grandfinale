package br.com.fiap.fintechgrandfinale.domain.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {
    private static final String DEFAULT_FORMAT = "dd/MM/yyyy";

    public static LocalDate parseDate(String dateString) {
        return parseDate(dateString, DEFAULT_FORMAT);
    }

    public static LocalDate parseDate(String dateString, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        try {
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Error parsing date: " + dateString + ". Expected format: " + format, e);
        }
    }

    public static String formatLocalDateTime(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return localDateTime.format(formatter);
    }
}
