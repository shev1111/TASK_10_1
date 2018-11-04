package com.shev.amazon_data.selenium_util;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class WebDriverManager {
    private static final String USER_DIR = System.getProperty("user.dir");
    private static final String SEP = System.getProperty("file.separator");
    private static final String PATH_TO_DRIVER = USER_DIR+SEP+"lib"+SEP+"chromedriver.exe";

    public static WebDriver getChromeWebDriver(){

        System.setProperty("webdriver.chrome.driver", PATH_TO_DRIVER);
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
        Timer.waitSeconds(2);
        return driver;
    }
}
