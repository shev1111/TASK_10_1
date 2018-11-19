package com.shev.amazon_data;

import com.shev.amazon_data.service.RegisterService;
import com.shev.amazon_data.service.SearchAmazonService;
import org.openqa.selenium.WebDriver;

public class AmazonRegisterMain {
    public static void main(String[] args) {
        /*WebDriver driver = RegisterService.registerUser("Tom Cat", "omt76543212@gmai.com", "12345qwerty6789");
        driver.quit();*/

        SearchAmazonService.searchByASIN("B07CTHN94Z");
    }
}
