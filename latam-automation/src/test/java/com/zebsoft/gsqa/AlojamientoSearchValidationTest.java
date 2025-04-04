package com.zebsoft.gsqa;

import java.time.LocalDate;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;
import com.zebsoft.gsqa.factories.PlaywrightFactory;
import com.zebsoft.gsqa.pages.HomePage;

public class AlojamientoSearchValidationTest {
    HomePage homePage;

    @BeforeClass
    public void setUp() {
        homePage = new HomePage();
    }

    //@Test
    public void verifyEmptyDestinationErrorMessage() {
        homePage.navigate("https://www.latamairlines.com/co/es");
        
        homePage.clickHotelTab();
        
        homePage.clickSearchButton();

        homePage.verifyErrorMessageVisible();
    }


    @Test
    public void verifySearchWithValidDestination() {
        homePage.navigate("https://www.latamairlines.com/co/es");
        
        homePage.clickHotelTab();
        
        homePage.enterDestination("Cartagena", "Colombia");

        homePage.setStartDate(LocalDate.now());

        homePage.waitForTimeout(500);

        homePage.setEndDate(LocalDate.now().plusDays(1));   

        homePage.clickSearchButton();

        homePage.waitForTimeout(500);

        homePage.isValidBookingRedirection();
    }

    @AfterClass
    public void tearDown() {
        PlaywrightFactory.closePlaywright();
    }
}