package com.shev.amazon_data.dao;

import com.shev.amazon_data.model.Item;
import com.shev.amazon_data.model.SimpleItem;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemsDAO {
    private static Logger logger = Logger.getLogger(ItemsDAO.class.getName());

    public static Item insertItem(Item item){
        String sql = "INSERT INTO items (asin, url, price, title, availability) VALUES(?,?,?,?,?)";
        try (Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,item.getAsin());
            preparedStatement.setString(2,item.getUrl());
            preparedStatement.setInt(3,item.getPriceCents());
            preparedStatement.setString(4,item.getProductTitle());
            preparedStatement.setString(5,item.getAvailability());
            preparedStatement.execute();
            logger.info("Item  "+item.getProductTitle()+" was inserted");
            return item;
        } catch (SQLException e) {
            logger.error("SQL error: "+e.getMessage());
        }
        logger.error("Item  "+item.getProductTitle()+" was not inserted");
        return new SimpleItem();
    }

    public static Item retrieveItem(String asin){
        String sql = "SELECT asin, url, price, title, availability FROM items WHERE asin =?";
        Item item = new SimpleItem();
        try (Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,asin);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            item.setAsin(resultSet.getString("asin"));
            item.setUrl(resultSet.getString("url"));
            item.setPriceCents(resultSet.getInt("price"));
            item.setProductTitle(resultSet.getString("title"));
            item.setAvailability(resultSet.getString("availability"));
            resultSet.close();
            logger.info("Item "+item.getProductTitle()+" was received");
            return item;
        } catch (SQLException e) {
            logger.error("SQL error: "+e.getMessage());
        }
        logger.info("Item "+item.getProductTitle()+" was not received");
        return new SimpleItem();
    }

    public static boolean updateItem(Item item){
        String sql = "UPDATE items SET url=?, price=?, title=?, availability =? WHERE asin =?";
        try (Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,item.getUrl());
            preparedStatement.setInt(2,item.getPriceCents());
            preparedStatement.setString(3, item.getProductTitle());
            preparedStatement.setString(4, item.getAvailability());
            preparedStatement.setString(5, item.getAsin());
            preparedStatement.execute();
            logger.info("Item "+item.getAsin()+" was updated");
            return true;
        } catch (SQLException e) {
            logger.error("SQL error: "+e.getMessage());
        }
        logger.error("Item "+item.getAsin()+" was not updated");
        return false;
    }

    public static boolean deleteItem(String asin){
        String sql = "DELETE FROM items WHERE asin =?";
        try (Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,asin);
            preparedStatement.execute();
            logger.info("Item "+asin+" was deleted");
            return true;
        } catch (SQLException e) {
            logger.error("SQL error: "+e.getMessage());
        }
        logger.error("Item "+asin+" was not deleted");
        return false;
    }
}
