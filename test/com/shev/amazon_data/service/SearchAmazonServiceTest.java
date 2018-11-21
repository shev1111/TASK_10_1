package com.shev.amazon_data.service;


import org.junit.Test;

import static org.junit.Assert.*;

public class SearchAmazonServiceTest {
    private static final String ASIN = "B07FPR6FMJ";
    @Test
    public void searchByASIN() throws Exception {
        String testLink = SearchAmazonService.searchByASIN(ASIN);
        assertNotNull(testLink);
        assertTrue(testLink.contains("keywords=B07FPR6FMJ"));
    }



}
