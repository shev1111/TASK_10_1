package com.shev.amazon_data.service;

import com.shev.amazon_data.utils.selenium_util.Timer;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BuyingService {
    private static Logger logger = Logger.getLogger(BuyingService.class.getName());

    public static WebDriver addItemToCartByLink(String link, String login, String password){
        WebDriver webDriver = RegisterService.signInUser(login, password);
        if(webDriver==null)return null;
        boolean addToCart = addToCart(link, webDriver);
        if (addToCart) return webDriver;
        return null;
    }

    public static WebDriver addItemToCartByASIN(String asin, String login, String password){
        String itemLink = SearchAmazonService.searchByASIN(asin);
        if(itemLink!=null){
            WebDriver webDriver = RegisterService.signInUser(login, password);
            if(webDriver==null)return null;
            boolean addToCart = addToCart(itemLink, webDriver);
            if(addToCart)return webDriver;
            webDriver.quit();
            return null;
        }
        return null;
    }

    private static boolean addToCart(String itemLink, WebDriver webDriver) {
        try {
            webDriver.get(itemLink);
            Timer.waitSeconds(4);
            WebElement addToCartElement = webDriver.findElement(By.id("add-to-cart-button"));
            addToCartElement.click();
            Timer.waitSeconds(4);
            if(checkIfItemAddedToCart(webDriver)) return true;
        } catch (NoSuchElementException e) {
            String errMessage = e.getMessage();
            int spaceIndex = errMessage.indexOf("\n");
            logger.error(errMessage.substring(0,spaceIndex));
        } catch (NullPointerException e){
            logger.error(e.getMessage());
        }
        webDriver.quit();
        return false;
    }

    private static boolean protectionPlan(WebDriver webDriver){
        try {
            WebElement protectionContainer = webDriver.findElement(By.id("abbWrapper"));
            WebElement protectionPlan = protectionContainer.findElement(By.cssSelector("h6[class='nonacw-label a-text-bold']"));
            if(protectionPlan.getText().contains("Add a Protection Plan:")) return true;
        } catch (NoSuchElementException e) {
            String errMessage = e.getMessage();
            int spaceIndex = errMessage.indexOf("\n");
            logger.error(errMessage.substring(0,spaceIndex));
        }
        return false;
    }

    public static boolean checkIfItemAddedToCart(WebDriver webDriver){
        try {
            WebElement orderConfirm = webDriver.findElement(By.id("huc-v2-order-row-confirm-text"));
            if(orderConfirm.getText().contains("Added to Cart"))return true;
        } catch (NoSuchElementException e) {
            String errMessage = e.getMessage();
            int spaceIndex = errMessage.indexOf("\n");
            logger.error(errMessage.substring(0,spaceIndex));
        }
        return false;
    }

}
