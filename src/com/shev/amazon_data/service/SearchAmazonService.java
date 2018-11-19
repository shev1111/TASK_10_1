package com.shev.amazon_data.service;

import com.shev.amazon_data.utils.selenium_util.Timer;
import com.shev.amazon_data.utils.selenium_util.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchAmazonService {
    private static Logger logger = Logger.getLogger(SearchAmazonService.class.getName());

/*@param String Amazon Standard Identification Number (ASIN)
* @return String url of product which responds to ASIN*/
    public static String searchByASIN(String asin){
        WebDriver webDriver = WebDriverManager.getChromeWebDriver();
        try {
            webDriver.get("https://www.amazon.com");
            Timer.waitSeconds(3);
            WebElement searchBox = webDriver.findElement(By.id("twotabsearchtextbox"));
            WebElement searchButton = webDriver.findElement(By.className("nav-input"));
            searchBox.sendKeys(asin);
            Timer.waitSeconds(3);
            searchButton.submit();
            Timer.waitSeconds(3);
            String currentPage = webDriver.getCurrentUrl();
            webDriver.get(currentPage);
            Timer.waitSeconds(3);
            WebElement resultElement = webDriver.findElement(By.id("result_0"));
            WebElement aTagElement = resultElement.findElement(By.tagName("a"));
            String url = aTagElement.getAttribute("href");
            webDriver.quit();
            if(url!=null) return url;
        } catch (NoSuchElementException e) {
            logger.error("There is no output result of search");
        }
        webDriver.quit();
        return null;
    }
}
