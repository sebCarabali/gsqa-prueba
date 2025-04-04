package com.zebsoft.gsqa.alojamieto;

import java.time.LocalDate;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.zebsoft.gsqa.factories.PlaywrightFactory;
import com.zebsoft.gsqa.pages.HomePage;

public class ValidSearchTest {

    HomePage homePage;

    @BeforeClass
    public void setUp() {
        homePage = new HomePage();
    }

    @Test(priority = 1)
    public void verifySearchWithValidDestination() {
        homePage.navigate("https://www.latamairlines.com/co/es");
        
        homePage.waitForTimeout(500);

        homePage.clickHotelTab();
        
        homePage.enterDestination("Bogotá", "Colombia");

        homePage.setStartDate(LocalDate.now().plusDays(7));

        homePage.waitForTimeout(500);

        homePage.setEndDate(LocalDate.now().plusDays(14));   

        homePage.clickSearchButton();

        homePage.waitForTimeout(500);

        homePage.isValidBookingRedirection();
    }

    @AfterClass
    public void tearDown() {
        PlaywrightFactory.closePlaywright();
    }
}
