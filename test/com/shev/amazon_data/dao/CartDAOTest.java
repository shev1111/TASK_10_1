package com.shev.amazon_data.dao;


import com.shev.amazon_data.model.CartItem;
import com.shev.amazon_data.model.Item;
import com.shev.amazon_data.model.SimpleItem;
import com.shev.amazon_data.model.User;
import org.junit.Test;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class CartDAOTest {


    private static String testAsin = "ZJB25DE87";
    private static String testLogin = "test@gmail.com";
    private static String testUserName = "Test";
    private static String testPassword = "test12345";
    private static String testUrl = "https://www.amazon.com/laptop/test";
    private static String testTitle = "Test";
    private static String testAvailability = "In Stock";
    private static int testPrice = 12345;
    private static final String USER_DIR = System.getProperty("user.dir");
    private static final String SEP = System.getProperty("file.separator");
    private static final String CSV_FILE_TEST = USER_DIR+SEP+"src"+SEP+"com"+SEP+"shev"+SEP+"amazon_data"+SEP+"data"+SEP+"cart_period.csv";
    @Test
    public void insertCartItem() throws Exception {
        CartItem testCartItem = new CartItem(testAsin,testLogin);
        Item testItem = new SimpleItem(testAsin,testTitle,testPrice,testAvailability,testUrl);
        User testUser = new User(testLogin,testUserName,testPassword);
        ItemsDAO.insertItem(testItem);
        UserDAO.createUser(testUser);
        CartItem cartItem = CartDAO.insertCartItem(testCartItem);
        assertNotNull(cartItem);
        assertEquals(testAsin,cartItem.getItem_asin());
        assertEquals(testLogin,cartItem.getUser_login());
        boolean delete = ItemsDAO.deleteItem(testItem.getAsin());
        assertTrue(delete);
    }

    @Test
    public void retrieveCartItemByID() throws Exception {
        CartItem testCartItem = new CartItem(testAsin,testLogin);
        Item testItem = new SimpleItem(testAsin,testTitle,testPrice,testAvailability,testUrl);
        User testUser = new User(testLogin,testUserName,testPassword);
        ItemsDAO.insertItem(testItem);
        UserDAO.createUser(testUser);
        CartItem cartItem = CartDAO.insertCartItem(testCartItem);
        CartItem testRetrieveCartItem = CartDAO.retrieveCartItemByID(17);
        assertNotNull(cartItem);
        assertEquals(testAsin,testRetrieveCartItem.getItem_asin());
        assertEquals(testLogin,testRetrieveCartItem.getUser_login());
        boolean delete = ItemsDAO.deleteItem(testItem.getAsin());
        assertTrue(delete);
    }

    @Test
    public void cartByPeriodToCSV() throws ParseException {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        String from = "2018-12-03 19:27:11";
        String to = "2018-12-08 10:30:45";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date dateFrom = dateFormat.parse(from);
        Date dateTo = dateFormat.parse(to);
        File csv = new File(CSV_FILE_TEST);
        assertTrue(CartDAO.cartByPeriodToCSV(dateFrom.getTime(),dateTo.getTime()));
        assertTrue(csv.exists());
        csv.delete();
    }

}
