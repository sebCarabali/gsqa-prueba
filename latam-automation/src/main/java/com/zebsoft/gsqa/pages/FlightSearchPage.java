package com.zebsoft.gsqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.zebsoft.gsqa.components.DatePickerComponent;
import com.zebsoft.gsqa.components.OriginAutocompleteComponent;
import com.zebsoft.gsqa.utils.WebDriverManagerUtil;

public class FlightSearchPage extends BasePage {

    private static final String ORIGIN_INPUT_ID = "fsb-origin--text-field";
    private static final String DESTINATION_INPUT_ID = "fsb-destination--text-field";
    private static final String DEPARTURE_DATE_INPUT_ID = "fsb-departure-date--text-field";
    private static final String RETURN_DATE_INPUT_ID = "fsb-return-date--text-field";
    private static final String SEARCH_BUTTON_ID = "fsb-search-flights";

    private final DatePickerComponent datePicker;
    
    private final OriginAutocompleteComponent originAutocomplete;
    private final By destinationInput = By.id(DESTINATION_INPUT_ID);
    private final By departureDateInput = By.id(DEPARTURE_DATE_INPUT_ID);
    private final By returnDateInput = By.id(RETURN_DATE_INPUT_ID);
    private final By searchButton = By.id(SEARCH_BUTTON_ID);
    private final By resultsContainer = By.cssSelector("[data-testid='flight-results-container']");

    public FlightSearchPage() {
        super(WebDriverManagerUtil.getDriver());
        this.datePicker = new DatePickerComponent(driver);
        this.originAutocomplete = new OriginAutocompleteComponent(driver);
    }

    /**
     * Ingresa ciudad de origen
     * 
     * @param origin Ciudad de origen (ej. "MEX (Ciudad de México)")
     */
    public void enterOrigin(String origin) {
        originAutocomplete.enterText(origin);
        originAutocomplete.selectOptionByText(origin);
    }

    /**
     * Ingresa ciudad de destino
     * 
     * @param destination Ciudad de destino (ej. "SCL (Santiago de Chile)")
     */
    public void enterDestination(String destination) {
        type(destinationInput, destination);
        selectFromDropdown(destination);
    }

    /**
     * Selecciona fecha de ida
     * 
     * @param daysFromToday Número de días a partir de hoy (ej. 7 para una semana
     *                      después)
     */
    public void setDepartureDate(int daysFromToday) {
        click(departureDateInput); // Abre el date picker
        datePicker.selectFutureDate(daysFromToday);
    }

    /**
     * Selecciona fecha de vuelta (para viajes ida y vuelta)
     * 
     * @param daysFromToday Número de días a partir de hoy
     */
    public void setReturnDate(int daysFromToday) {
        click(returnDateInput); // Abre el date picker
        datePicker.selectFutureDate(daysFromToday);
    }

     // Métodos de acciones básicas
    
    public void clickSearch() {
        click(searchButton);
    }
    
    // Métodos de validación
    
    public boolean areResultsDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(resultsContainer))
                  .isDisplayed();
    }
    
    public String getSelectedOrigin() {
        return originAutocomplete.getSelectedValue();
    }

    public String getSelectedDestination() {
        return driver.findElement(destinationInput).getAttribute("value");
    }
    
    public String getSelectedDepartureDate() {
        return driver.findElement(departureDateInput).getAttribute("value");
    }
    
    public String getSelectedReturnDate() {
        return driver.findElement(returnDateInput).getAttribute("value");
    }
    
    // Métodos auxiliares
    
    private void selectFromDropdown(String value) {
        By dropdownOption = By.xpath(String.format("//div[@role='option' and contains(., '%s')]", value));
        wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownOption)).click();
    }
    
    public boolean isOriginAutocompleteVisible() {
        return originAutocomplete.isOptionsVisible();
    }
    
    public int getOriginOptionsCount() {
        return originAutocomplete.getOptionsCount();
    }
}
