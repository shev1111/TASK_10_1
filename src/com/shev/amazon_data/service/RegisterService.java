package com.shev.amazon_data.service;

import com.shev.amazon_data.selenium_util.Timer;
import com.shev.amazon_data.selenium_util.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterService {

    private static Logger logger = Logger.getLogger(AmazonServiceItemRetrieve.class.getName());

    private static String getRegisterLink(WebDriver webDriver) {
        WebElement registerElement = webDriver.findElement(By.id("nav-flyout-ya-newCust"));
        WebElement aElement = registerElement.findElement(By.tagName("a"));
        return aElement.getAttribute("href");
    }

    public static WebDriver registerUser(String name, String login, String userPassword){
        WebDriver webDriver = WebDriverManager.getChromeWebDriver();
        webDriver.get("https://www.amazon.com");
        Timer.waitSeconds(3);
        String registerLink = getRegisterLink(webDriver);
        webDriver.get(registerLink);
        Timer.waitSeconds(3);
        WebElement inputName = webDriver.findElement(By.id("ap_customer_name"));
        WebElement inputEmail = webDriver.findElement(By.id("ap_email"));
        WebElement inputPass = webDriver.findElement(By.id("ap_password"));
        WebElement inputCheckPass = webDriver.findElement(By.id("ap_password_check"));
        WebElement inputSubmit = webDriver.findElement(By.id("continue"));

        inputName.sendKeys(name);
        Timer.waitSeconds(3);
        inputEmail.sendKeys(login);
        Timer.waitSeconds(3);
        inputPass.sendKeys(userPassword);
        Timer.waitSeconds(3);
        inputCheckPass.sendKeys(userPassword);
        Timer.waitSeconds(3);
        inputSubmit.submit();
        Timer.waitSeconds(3);
        String currentPage = webDriver.getCurrentUrl();
        webDriver.get(currentPage);
        Timer.waitSeconds(10);
        return webDriver;

    }

    public static boolean checkIfRegistered (WebDriver driver, String inputUserName) {
        String checkUserName;
        try {
            WebElement registeredUserNameElement = driver.findElement(By.cssSelector(".a-section.a-spacing-none.customer-name"));
            checkUserName = registeredUserNameElement.findElement(By.tagName("a")).getText();
            String firstName;
            if (inputUserName.contains(" ")){
                firstName = inputUserName.substring(0, inputUserName.indexOf(' '));
            }else {
                firstName = inputUserName;
            }
            if(checkUserName.contains(firstName))
                logger.info( inputUserName+" user was registered");
                return true;

        } catch (NoSuchElementException e) {
            logger.error(inputUserName+" user was not registered");
        }
        return false;

    }
}
