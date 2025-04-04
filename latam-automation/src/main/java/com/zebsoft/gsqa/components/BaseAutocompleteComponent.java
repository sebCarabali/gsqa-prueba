package com.zebsoft.gsqa.components;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;

public abstract class BaseAutocompleteComponent {
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    @FindBy(css = "input[role='combobox']")
    protected WebElement inputField;
    
    @FindBy(css = "[role='listbox']")
    protected WebElement optionsContainer;
    
    @FindBy(css = "[role='option']")
    protected List<WebElement> options;
    
    @FindBy(css = "[role='status']")
    protected WebElement statusMessage;

    public BaseAutocompleteComponent(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void enterText(String text) {
        inputField.clear();
        inputField.sendKeys(text);
        waitForOptionsToLoad();
    }

    public void selectOptionByText(String optionText) {
        wait.until(ExpectedConditions.visibilityOf(optionsContainer));
        getOptionByText(optionText).click();
    }

    public void selectOptionByIndex(int index) {
        wait.until(ExpectedConditions.visibilityOf(optionsContainer));
        if (index >= 0 && index < options.size()) {
            options.get(index).click();
        } else {
            throw new NoSuchElementException("Option index out of bounds: " + index);
        }
    }

    public String getSelectedValue() {
        return inputField.getAttribute("value");
    }

    public boolean isOptionsVisible() {
        try {
            return optionsContainer.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public int getOptionsCount() {
        return options.size();
    }

    protected abstract void waitForOptionsToLoad();
    protected abstract WebElement getOptionByText(String optionText);
}