package com.zebsoft.gsqa.components;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.zebsoft.gsqa.utils.DateUtils;

public class DatePickerComponent {
     private final WebDriver driver;
    
    // Locators
    private final By departureField = By.id("fsb-departure--text-field");
    private final By datePickerPopup = By.cssSelector("[data-testid='datepicker-desktop']");
    private final By nextMonthButton = By.cssSelector("[data-testid='datepicker-desktop--next-month-button']");
    
    public DatePickerComponent(WebDriver driver) {
        this.driver = driver;
    }
    
    public void selectFutureDate(int daysFromToday) {
        String targetDate = DateUtils.getFormattedFutureDate(daysFromToday);
        String[] dateParts = targetDate.split(" ");
        String dayOfWeek = dateParts[0]; // ej: "jue"
        String dayNumber = dateParts[1]; // ej: "17"
        String monthAbbr = dateParts[2]; // ej: "abr"
        
        openDatePicker();
        navigateToCorrectMonth(monthAbbr);
        selectDayInCalendar(dayNumber);
    }
    
    private void openDatePicker() {
        driver.findElement(departureField).click();
        waitForDatePickerToBeVisible();
    }
    
    private void waitForDatePickerToBeVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(datePickerPopup));
    }
    
    private void navigateToCorrectMonth(String targetMonthAbbr) {
        By currentMonthLocator = By.cssSelector("[data-testid='datepicker-desktop--month-title']");
        
        while (true) {
            String currentMonth = driver.findElement(currentMonthLocator).getText().toLowerCase();
            if (currentMonth.contains(targetMonthAbbr)) {
                break;
            }
            driver.findElement(nextMonthButton).click();
        }
    }
    
    private void selectDayInCalendar(String dayNumber) {
        By dayLocator = By.xpath(String.format(
            "//div[contains(@class, 'CalendarDay') and not(contains(@class, 'disabled'))]" +
            "//div[text()='%s']", dayNumber));
        
        driver.findElement(dayLocator).click();
    }
}
