package com.zebsoft.gsqa.components;

import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OriginAutocompleteComponent extends BaseAutocompleteComponent {

    @FindBy(css = "label[data-testid='fsb-origin--text-field__label']")
    private WebElement label;
    
    @FindBy(css = "div[data-testid='fsb-origin--autocomplete__popper']")
    private WebElement popper;

    public OriginAutocompleteComponent(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void waitForOptionsToLoad() {
        wait.until(d -> {
            String message = statusMessage.getText();
            return message.contains("opción disponible") || 
                   message.contains("opciones disponibles") ||
                   message.contains("No se encontraron resultados");
        });
    }

    @Override
    protected WebElement getOptionByText(String optionText) {
        return options.stream()
            .filter(option -> option.getText().contains(optionText))
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException("Option not found: " + optionText));
    }

    // Component-specific methods
    public String getLabelText() {
        return label.getText();
    }

    public boolean isPopperVisible() {
        try {
            return popper.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
