package com.zebsoft.gsqa.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtils {
    public static String getFormattedFutureDate(int daysToAdd) {
        LocalDate futureDate = LocalDate.now().plusDays(daysToAdd);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE d MMM", new Locale("es", "ES"));
        return futureDate.format(formatter).toLowerCase();
    }

    public static String getFutureDateForInput(int daysToAdd) {
        LocalDate futureDate = LocalDate.now().plusDays(daysToAdd);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return futureDate.format(formatter);
    }
}
