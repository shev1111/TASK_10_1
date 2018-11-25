package com.shev.amazon_data.utils;

import org.apache.log4j.Logger;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JdbcPropManager {
    private static Logger logger = Logger.getLogger(JdbcPropManager.class.getName());

    public static String getProperty(String property) {
        try {
            InputStream inputStream = new FileInputStream("src/jdbc.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            String receiveProp = properties.getProperty(property);
            if(receiveProp!=null)return receiveProp;
            logger.error("There is no such property as '"+property+"'");
        } catch (FileNotFoundException e) {
            logger.error("no jdbc.properties file");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return "";
    }


}
