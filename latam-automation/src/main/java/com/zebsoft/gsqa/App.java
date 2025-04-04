package com.zebsoft.gsqa;

import java.time.LocalDate;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.zebsoft.gsqa.components.DatePicker;

/**
 * Hello world!
 *
 */
public class App {
    private static final String HOTEL_TAB_BUTTON = "#id-tab-hotel";
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {

            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            // Abre la página con el DatePicker
            page.navigate("https://www.latamairlines.com/co/es");
            System.out.println("Page loaded: " + page.title());

            page.locator(HOTEL_TAB_BUTTON).click();
            page.waitForTimeout(1000);
            // Paso 1: Click en el botón que abre el DatePicker
            DatePicker datePicker = new DatePicker(page);
            datePicker.selectDate("hotel-searchbox-datepicker-startdate-field--button-input", LocalDate.now());
            page.waitForTimeout(500);
            datePicker.selectDate("hotel-searchbox-datepicker-enddate-field--button-input", LocalDate.now().plusDays(1));
            // Esperar un poco para ver la selección antes de cerrar
            page.waitForTimeout(3000);

            // Cerrar navegador
            browser.close();
        }

    }
}
