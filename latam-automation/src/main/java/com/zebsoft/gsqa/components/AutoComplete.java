package com.zebsoft.gsqa.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public abstract class AutoComplete {
    protected final Page page;
    private final String inputSelector;
    private final String optionsListSelector;
    private final String optionSelector;

    public AutoComplete(Page page, String inputSelector, String optionsListSelector, String optionSelector) {
        this.page = page;
        this.inputSelector = inputSelector;
        this.optionsListSelector = optionsListSelector;
        this.optionSelector = optionSelector;
    }

    public void searchAndSelect(String searchText, String optionToSelect) {
        Locator input = page.locator(inputSelector);
        input.fill(searchText);

        page.waitForSelector(optionsListSelector,
                new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
        System.out.println("Options list visible: " + page.locator(optionsListSelector).isVisible());
        page.locator(optionSelector + ":has-text('" + optionToSelect + "')").first().click();
        System.out.println("Option selected: " + optionToSelect);
        page.waitForSelector(optionsListSelector,
                new Page.WaitForSelectorOptions().setState(WaitForSelectorState.HIDDEN));
    }

    public void searchAndSelectFirstOption(String searchText) {
        Locator input = page.locator(inputSelector);
        input.fill(searchText);

        page.waitForSelector(optionsListSelector,
                new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));

        page.locator(optionSelector).first().click();

        page.waitForSelector(optionsListSelector,
                new Page.WaitForSelectorOptions().setState(WaitForSelectorState.HIDDEN));
    }
}
