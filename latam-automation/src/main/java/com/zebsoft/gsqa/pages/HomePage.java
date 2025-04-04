package com.zebsoft.gsqa.pages;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Pattern;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.zebsoft.gsqa.components.DatePicker;
import com.zebsoft.gsqa.components.HotelDestinationAutoComplete;

public class HomePage extends BasePage {
    private static final String HOTEL_TAB_BUTTON = "#id-tab-hotel";
    private static final String HOTEL_TAB_PANE = "#tabpanel-hotel";
    private static final String SEARCH_BUTTON = "button:has-text('Buscar')";
    private static final String ERROR_MESSAGE = "text=Ingresa un destino válido";
    private static final String CHECKIN_INPUT = "hotel-searchbox-datepicker-startdate-field--button-input";
    private static final String CHECKOUT_INPUT = "hotel-searchbox-datepicker-enddate-field--button-input";
    private static final String DATE_OPTION = "[aria-label*='%s']";
    private static final String BOOKING_URL_PATTERN = "^https://sp\\.booking\\.com/.*";

    private final HotelDestinationAutoComplete destinationAutocomplete;
    private final DatePicker datePicker;

    public HomePage() {
        super();
        destinationAutocomplete = new HotelDestinationAutoComplete(page);
        datePicker = new DatePicker(page);
    }

    public void clickHotelTab() {
        click(HOTEL_TAB_BUTTON);
        waitForTimeout(1000);
    }

    public void verifyHotelTabVisible() {
        verifyElementVisible(HOTEL_TAB_PANE);
    }

    public void clickSearchButton() {
        click(SEARCH_BUTTON);
    }

    public void verifyErrorMessageVisible() {
        PlaywrightAssertions.assertThat(page.locator(ERROR_MESSAGE)).isVisible();
    }

    public void enterDestination(String destino, String optionToSelect) {
        destinationAutocomplete.searchAndSelect(destino, optionToSelect);
    }

   public void setStartDate(LocalDate date) {
        setDate(CHECKIN_INPUT, date);
    }

    public void setEndDate(LocalDate date) {
        setDate(CHECKOUT_INPUT, date);
    }

    private void setDate(String inputSelector, LocalDate date) {
        datePicker.selectDate(inputSelector, date);
    }

    public void validateNavigationToSearchResults() {
        page.waitForURL(Pattern.compile(BOOKING_URL_PATTERN));
    }

    public boolean isValidBookingRedirection() {
        return page.url().matches(BOOKING_URL_PATTERN);
    }
}
