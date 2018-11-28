package com.shev.amazon_data.dao;

import com.shev.amazon_data.model.CartItem;
import com.shev.amazon_data.model.Item;
import com.shev.amazon_data.model.SimpleItem;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartDAO {
    private static Logger logger = Logger.getLogger(CartDAO.class.getName());

    public static CartItem insertCartItem(CartItem item){
        String sql = "INSERT INTO cart (item_asin, user_login) VALUES(?,?)";
        try (Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,item.getItem_asin());
            preparedStatement.setString(2,item.getUser_login());
            preparedStatement.execute();
            logger.info("CartItem  "+item.getItem_asin()+" was inserted");
            return item;
        } catch (SQLException e) {
            logger.error("SQL error: "+e.getMessage());
        }
        logger.error("Cart Item  "+item.getItem_asin()+" was not inserted");
        return new CartItem();
    }

    public static CartItem retrieveCartItemByID(int id){
        String sql = "SELECT item_order_id, item_asin, user_login, times FROM cart WHERE item_order_id =?";
        CartItem item = new CartItem();
        try (Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                item.setItem_order_id(resultSet.getInt("item_order_id"));
                item.setItem_asin(resultSet.getString("item_asin"));
                item.setUser_login(resultSet.getString("user_login"));
                item.setTimes(resultSet.getString("times"));
            }
            logger.info("Cart Item "+item.getItem_asin()+" was received");
            return item;
        } catch (SQLException e) {
            logger.error("SQL error: "+e.getMessage());
        }
        logger.info("Cart Item "+item.getItem_asin()+" was not received");
        return new CartItem();
    }

    public static boolean updateCartItemByID(CartItem item){
        String sql = "UPDATE cart SET user_login=? WHERE id=?";
        try (Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,item.getUser_login());
            preparedStatement.setInt(2,item.getItem_order_id());
            preparedStatement.execute();
            logger.info("Cart Item "+item.getItem_asin()+" was updated");
            return true;
        } catch (SQLException e) {
            logger.error("SQL error: "+e.getMessage());
        }
        logger.error("Cart Item "+item.getItem_asin()+" was not updated");
        return false;
    }

    public static boolean deleteItemByID(String id){
        String sql = "DELETE FROM cart WHERE id =?";
        try (Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,id);
            preparedStatement.execute();
            logger.info("Cart Item "+id+" was deleted");
            return true;
        } catch (SQLException e) {
            logger.error("SQL error: "+e.getMessage());
        }
        logger.error("Item "+id+" was not deleted");
        return false;
    }
}
