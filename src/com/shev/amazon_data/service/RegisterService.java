package com.shev.amazon_data.service;

import com.shev.amazon_data.utils.selenium_util.Timer;
import com.shev.amazon_data.utils.selenium_util.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;

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

    public static WebDriver signInUser(String email, String userPassword){
        WebDriver webDriver = WebDriverManager.getChromeWebDriver();
        webDriver.get("https://www.amazon.com/ap/signin?_encoding=UTF8&" +
                "ignoreAuthState=1&openid.assoc_handle=usflex&openid.claimed_id=http%3A%2F%2Fspecs.openid.net" +
                "%2Fauth%2F2.0%2Fidentifier_select&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2" +
                "Fidentifier_select&openid.mode=checkid_setup&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0" +
                "&openid.ns.pape=http%3A%2F%2Fspecs.openid.net%2Fextensions%2Fpape%2F1.0&openid.pape.max_auth_age=0" +
                "&openid.return_to=https%3A%2F%2Fwww.amazon.com%2Fref%3Dnav_signin&switch_account=");
        Timer.waitSeconds(3);
        WebElement login = webDriver.findElement(By.id("ap_email"));
        WebElement pass = webDriver.findElement(By.id("ap_password"));
        WebElement signIn = webDriver.findElement(By.id("signInSubmit"));
        Timer.waitSeconds(3);
        login.sendKeys(email);
        Timer.waitSeconds(3);
        pass.sendKeys(userPassword);
        Timer.waitSeconds(3);
        signIn.submit();
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
