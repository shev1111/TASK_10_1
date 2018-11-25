package com.shev.amazon_data.dao;



import com.shev.amazon_data.utils.JdbcPropManager;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static Logger logger = Logger.getLogger(ConnectionDB.class.getName());
    private static String host = JdbcPropManager.getProperty("pg.host");
    private static String port = JdbcPropManager.getProperty("pg.port");
    private static String user = JdbcPropManager.getProperty("pg.user_name");
    private static String password = JdbcPropManager.getProperty("pg.password");
    private static String db = JdbcPropManager.getProperty("pg.db_name");
    private static String url = "jdbc:postgresql://"+host+":"+port+"/"+db;
    private static String driverName = "org.postgresql.Driver";
    private static Connection connection;

    public static Connection getConnection() {
        try {
            Class.forName(driverName);
            try {
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                logger.error("Failed to create the database connection "+e);
            }
        } catch (ClassNotFoundException e) {
            logger.error("Driver not found. "+e);
        }
        logger.info("connection to "+db+" database was created");
        return connection;
    }
}
