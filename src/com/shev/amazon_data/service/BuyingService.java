package com.shev.amazon_data.service;

import com.shev.amazon_data.dao.CartDAO;
import com.shev.amazon_data.dao.ItemsDAO;
import com.shev.amazon_data.dao.UserDAO;
import com.shev.amazon_data.model.CartItem;
import com.shev.amazon_data.model.Item;
import com.shev.amazon_data.model.SimpleItem;
import com.shev.amazon_data.model.User;
import com.shev.amazon_data.utils.selenium_util.Timer;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BuyingService {
    private static Logger logger = Logger.getLogger(BuyingService.class.getName());

    public static WebDriver addItemToCartByLink(String link, String login, String password){
        WebDriver webDriver = RegisterService.signInUser(login, password);
        if(webDriver==null)return null;
        boolean addToCart = addToCart(link, webDriver);
        if (addToCart){
            setUserToDB(login, password);
            setItemToDB(link, login);
            return webDriver;
        }
        return null;
    }

    private static void setItemToDB(String link, String login) {
        AmazonServiceItemRetrieve itemRetrieve = new AmazonServiceItemRetrieve(link);
        Item item = itemRetrieve.getSimpleItem();
        if(item.getAsin()!=null){
            ItemsDAO.insertItem(item);
            CartItem cartItem = new CartItem(item.getAsin(), login);
            CartDAO.insertCartItem(cartItem);
        }
    }

    private static void setUserToDB(String login, String password) {
        User user = new User();
        user.setLogin(login);
        user.setPasword(password);
        int nameIndex = login.indexOf("@");
        user.setUser_name(login.substring(0,nameIndex));
        UserDAO.createUser(user);
    }

    public static WebDriver addItemToCartByASIN(String asin, String login, String password){
        String itemLink = SearchAmazonService.searchByASIN(asin);
        if(itemLink!=null){
            WebDriver webDriver = RegisterService.signInUser(login, password);
            if(webDriver==null)return null;
            boolean addToCart = addToCart(itemLink, webDriver);
            if(addToCart){
                setUserToDB(login, password);
                setItemToDB(itemLink, login);
                return webDriver;
            }
            webDriver.quit();
            return null;
        }
        return null;
    }

    private static boolean addToCart(String itemLink, WebDriver webDriver) {
        try {
            webDriver.get(itemLink);
            Timer.waitSeconds(4);
            WebElement addToCartElement = webDriver.findElement(By.id("add-to-cart-button-ubb"));
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

    public static boolean CartReportByPeriodCSV(String from, String to){
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            Date dateFrom = dateFormat.parse(from);
            Date dateTo = dateFormat.parse(to);
            if(dateFrom.getTime()>dateTo.getTime())return false;
            return CartDAO.cartByPeriodToCSV( dateFrom.getTime(), dateTo.getTime());
        } catch (ParseException e) {
            logger.error("Parse exception: "+e.getMessage());
        }
        return false;
    }

}
