package com.shev.amazon_data.dao;

import au.com.bytecode.opencsv.CSVWriter;
import com.shev.amazon_data.model.CartItem;
import com.shev.amazon_data.model.Item;
import com.shev.amazon_data.model.SimpleItem;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CartDAO {
    private static Logger logger = Logger.getLogger(CartDAO.class.getName());
    private static final String USER_DIR = System.getProperty("user.dir");
    private static final String SEP = System.getProperty("file.separator");
    private static final String CSV_FILE = USER_DIR+SEP+"src"+SEP+"com"+SEP+"shev"+SEP+"amazon_data"+SEP+"data"+SEP+"cart_period.csv";

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

    public static boolean cartByPeriodToCSV (long start, long end){
        Timestamp from = formatDate(start);
        Timestamp to   = formatDate(end);
        String sql = "SELECT * FROM  public.select_from_cart_by_period( ?, ?)";
        try (Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, from);
            preparedStatement.setObject(2, to);
            ResultSet resultSet = preparedStatement.executeQuery();
            CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE), ';', CSVWriter.NO_QUOTE_CHARACTER);
            writer.writeAll(resultSet, true);
            writer.close();
            while (resultSet.next()){
                System.out.println(resultSet.getString("asin"));
            }

            logger.info("csv was created, path to file "+CSV_FILE);
            return true;
        } catch (SQLException e) {
            logger.error("SQL error: "+e.getStackTrace());
        } catch (IOException e) {
            logger.error("IO error: "+e.getStackTrace());
        }
        logger.info("csv was not created");
        return false;
    }

    private static Timestamp formatDate(long date){
        Date time= new Date(date);
        return new java.sql.Timestamp(time.getTime());
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
