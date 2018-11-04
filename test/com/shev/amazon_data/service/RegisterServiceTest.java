package com.shev.amazon_data.service;


import org.junit.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.*;

public class RegisterServiceTest {

    @Test
    public void registerUser() throws Exception {
        String userName = "Tom Cat";
        String login = "omt76543213@gmai.com";
        String password = "12345qwerty6789";
        WebDriver driver = RegisterService.registerUser(userName, login, password);
        assertNotNull(driver);
        assertTrue(RegisterService.checkIfRegistered(driver,userName));
    }

}
