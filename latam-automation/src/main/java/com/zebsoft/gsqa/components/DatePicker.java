package com.zebsoft.gsqa.components;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class DatePicker {

    private final Page page;

    public DatePicker(Page page) {
        this.page = page;
    }

    public void selectDate(String datePickerTestId, LocalDate date) {
        page.locator("input[data-testid='" + datePickerTestId + "']").first().click();
        LocalDate today = LocalDate.now();
       
        String dayFormatted = String.format("%02d", date.getDayOfMonth()); // Asegura formato de dos dígitos (Ej: "05" para 5)
        String dateTestId = "date-" + today.getYear() + "-" + String.format("%02d", date.getMonthValue()) + "-" + dayFormatted;
        Locator diaElemento = page.locator("span[data-testid='" + dateTestId + "']");
        if (diaElemento.count() > 0) {
            diaElemento.click();
        }
    }
}
