package com.zebsoft.gsqa.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.LoadState;
import com.zebsoft.gsqa.factories.PlaywrightFactory;

public abstract class BasePage {
    protected Page page;

    public BasePage() {
        this.page = PlaywrightFactory.initPage();
    }

    public void navigate(String url) {
        page.navigate(url);
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void click(String selector) {
        page.locator(selector).click();
    }

    public void verifyElementVisible(String selector) {
        PlaywrightAssertions.assertThat(page.locator(selector)).isVisible();
    }

    public void waitForTimeout(int milliseconds) {
        page.waitForTimeout(milliseconds);
    }
}
