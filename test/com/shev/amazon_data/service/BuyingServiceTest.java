package com.shev.amazon_data.service;

import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;

public class BuyingServiceTest {
    private static final String LOGIN = "relenocewe@daabox.com";
    private static final String PASS = "12345678";
    @Test
    public void addItemToCartByLink(){
        String testLink = "https://www.amazon.com/Toshiba-32LF221U19-32-inch-720p-Smart/dp/B07FPR6FMJ/ref=sr_1_1?ie=UTF8&qid=1542827719&sr=8-1&keywords=B07FPR6FMJ";
        WebDriver testDriver = BuyingService.addItemToCartByLink(testLink, LOGIN, PASS);
        assertNotNull(testDriver);
        assertTrue(BuyingService.checkIfItemAddedToCart(testDriver));
        testDriver.quit();
    }

    @Test
    public void addItemToCartByASIN() {
        String testAsin = "B07FPR6FMJ";
        WebDriver testDriver = BuyingService.addItemToCartByASIN(testAsin, LOGIN, PASS);
        assertNotNull(testDriver);
        assertTrue(BuyingService.checkIfItemAddedToCart(testDriver));
        testDriver.quit();
    }


}
