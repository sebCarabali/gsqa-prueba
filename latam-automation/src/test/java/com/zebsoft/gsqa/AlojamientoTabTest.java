package com.zebsoft.gsqa;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.zebsoft.gsqa.factories.PlaywrightFactory;
import com.zebsoft.gsqa.pages.HomePage;

public class AlojamientoTabTest {

    HomePage homePage;

    @BeforeClass
    public void setUp() {
        homePage = new HomePage();
    }

    @Test
    public void verifyHotelTabDisplay() {
        homePage.navigate("https://www.latamairlines.com/co/es");
        
        homePage.clickHotelTab();
        
        homePage.verifyHotelTabVisible();
    }

    @AfterClass
    public void tearDown() {
        PlaywrightFactory.closePlaywright();
    }
}
