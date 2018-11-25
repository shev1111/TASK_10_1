package com.shev.amazon_data.dao;


import com.shev.amazon_data.model.Item;
import com.shev.amazon_data.model.SimpleItem;
import org.junit.Test;
import static org.junit.Assert.*;

public class ItemsDAOTest {
    private static String testAsin = "ZJB25DE87";
    private static String testUrl = "https://www.amazon.com/laptop/test";
    private static String testTitle = "Test";
    private static String testAvailability = "In Stock";
    private static String testUpdatedTitle = "Test1";
    private static int testPrice = 12345;
    @Test
    public void insertItem() throws Exception {
        Item testItem = new SimpleItem(testAsin,testTitle,testPrice,testAvailability,testUrl);
        Item item = ItemsDAO.insertItem(testItem);
        assertNotNull(item);
        assertEquals(testAsin,item.getAsin());
        assertEquals(testUrl,item.getUrl());
        assertEquals(testTitle,item.getProductTitle());
        assertEquals(testAvailability,item.getAvailability());
        assertEquals(testPrice,item.getPriceCents());
        boolean delete = ItemsDAO.deleteItem(item.getAsin());
        assertTrue(delete);
    }

    @Test
    public void retrieveItem() throws Exception {
        Item testItem = new SimpleItem(testAsin,testTitle,testPrice,testAvailability,testUrl);
        Item item = ItemsDAO.insertItem(testItem);
        Item receiveTestUser =ItemsDAO.retrieveItem(item.getAsin());
        assertNotNull(receiveTestUser);
        assertEquals(testAsin,receiveTestUser.getAsin());
        assertEquals(testUrl,receiveTestUser.getUrl());
        assertEquals(testTitle,receiveTestUser.getProductTitle());
        assertEquals(testAvailability,receiveTestUser.getAvailability());
        assertEquals(testPrice,receiveTestUser.getPriceCents());
        boolean delete = ItemsDAO.deleteItem(item.getAsin());
        assertTrue(delete);

    }

    @Test
    public void updateItem() throws Exception {
        Item testItem = new SimpleItem(testAsin,testTitle,testPrice,testAvailability,testUrl);
        ItemsDAO.insertItem(testItem);
        testItem.setProductTitle(testUpdatedTitle);
        boolean update = ItemsDAO.updateItem(testItem);
        assertTrue(update);
        Item receiveTestUser =ItemsDAO.retrieveItem(testItem.getAsin());
        assertNotNull(receiveTestUser);
        assertEquals(testAsin,receiveTestUser.getAsin());
        assertEquals(testUrl,receiveTestUser.getUrl());
        assertEquals(testUpdatedTitle,receiveTestUser.getProductTitle());
        assertEquals(testAvailability,receiveTestUser.getAvailability());
        assertEquals(testPrice,receiveTestUser.getPriceCents());
        boolean delete = ItemsDAO.deleteItem(receiveTestUser.getAsin());
        assertTrue(delete);
    }



}
