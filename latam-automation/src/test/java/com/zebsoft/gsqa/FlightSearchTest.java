package com.zebsoft.gsqa;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import com.zebsoft.gsqa.pages.FlightSearchPage;

public class FlightSearchTest {

    private FlightSearchPage flightSearchPage;

    @BeforeClass
    public void setUp() {
        flightSearchPage = new FlightSearchPage();
    }

    @Test
    public void testCompleteFlightSearch() {
        flightSearchPage.enterOrigin("MEX");
        flightSearchPage.enterDestination("SCL");
        flightSearchPage.setDepartureDate(7);
        flightSearchPage.setReturnDate(14);
        flightSearchPage.clickSearch();

        assertTrue(flightSearchPage.areResultsDisplayed());
    }

    @DataProvider(name = "originCities")
    public Object[][] provideOriginCities() {
        return new Object[][] {
                { "Bogotá", "Bogotá, BOG - Colombia" },
                { "Medellín", "Medellín, MDE - Colombia" },
                { "Cali", "Cali, CLO - Colombia" }
        };
    }

    @AfterClass
    public void tearDown() {
        if (flightSearchPage != null) {
            try {
                flightSearchPage = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        com.zebsoft.gsqa.utils.WebDriverManagerUtil.quitDriver();
    }
}
