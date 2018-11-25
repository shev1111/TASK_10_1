package com.shev.amazon_data.dao;

import com.shev.amazon_data.model.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private static Logger logger = Logger.getLogger(UserDAO.class.getName());

    public static User createUser(User user){
        String sql = "INSERT INTO users (login, user_name, password) VALUES(?,?,?)";
        try (Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getLogin());
            preparedStatement.setString(2,user.getUser_name());
            preparedStatement.setString(3,user.getPasword());
            preparedStatement.execute();
            logger.info("User with login "+user.getLogin()+" was created");
            return user;
        } catch (SQLException e) {
            logger.error("SQL error: "+e.getMessage());
        }
        logger.error("User with login "+user.getLogin()+" was not created");
        return new User();
    }

    public static User retrieveUser(String user_login){
        String sql = "SELECT login, user_name, password FROM users WHERE login =?";
        User user = new User();
        try (Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user_login);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user.setLogin(resultSet.getString("login"));
            user.setUser_name(resultSet.getString("user_name"));
            user.setPasword(resultSet.getString("password"));
            resultSet.close();
            logger.info("User with login "+user.getLogin()+" was received");
            return user;
        } catch (SQLException e) {
            logger.error("SQL error: "+e.getMessage());
        }
        logger.info("User with login "+user.getLogin()+" was not received");
        return new User();
    }

    public static boolean updateUser(User user){
        String sql = "UPDATE users SET user_name=?, password=? WHERE login =?";
        try (Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getUser_name());
            preparedStatement.setString(2,user.getPasword());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.execute();
            logger.info("User with login "+user.getLogin()+" was updated");
            return true;
        } catch (SQLException e) {
            logger.error("SQL error: "+e.getMessage());
        }
        logger.info("User with login "+user.getLogin()+" was not updated");
        return false;
    }

    public static boolean deleteUser(String user_login){
        String sql = "DELETE FROM users WHERE login =?";
        try (Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user_login);
            preparedStatement.execute();
            logger.info("User with login "+user_login+" was deleted");
            return true;
        } catch (SQLException e) {
            logger.error("SQL error: "+e.getMessage());
        }
        logger.error("User with login "+user_login+" was not deleted");
        return false;
    }





}
