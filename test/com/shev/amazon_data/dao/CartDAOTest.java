package com.shev.amazon_data.dao;


import com.shev.amazon_data.model.CartItem;
import com.shev.amazon_data.model.Item;
import com.shev.amazon_data.model.SimpleItem;
import com.shev.amazon_data.model.User;
import org.junit.Test;
import static org.junit.Assert.*;

public class CartDAOTest {
    private static String testAsin = "ZJB25DE87";
    private static String testLogin = "test@gmail.com";
    private static String testUserName = "Test";
    private static String testPassword = "test12345";
    private static String testUrl = "https://www.amazon.com/laptop/test";
    private static String testTitle = "Test";
    private static String testAvailability = "In Stock";
    private static String testUpdatedTitle = "Test1";
    private static int testPrice = 12345;
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

}
