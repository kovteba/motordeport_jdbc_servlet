package ua.nure.kovteba.finaltask.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.logging.Logger;

public class Connect {

    private static Logger LOG = Logger.getLogger(Connect.class.getName());

    private static final Properties PROPERTIES = new Properties();

    //Create connection
    public static Connection connect() {

        FileInputStream fileWithPropertiesForDB
                = null;
        try {
            fileWithPropertiesForDB = new FileInputStream("src/main/resources/db.config.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            PROPERTIES.load(fileWithPropertiesForDB);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url = PROPERTIES.getProperty("db.url");
        Properties properties = new Properties();
        properties.setProperty("user", PROPERTIES.getProperty("db.login"));
        properties.setProperty("password", PROPERTIES.getProperty("db.password"));
        properties.setProperty("serverTimezone", PROPERTIES.getProperty("db.serverTimeZone"));


        try {
            Connection connection = DriverManager.getConnection(url, properties);
            LOG.info("Connection to Store DB succesfull!");
            return connection;
        } catch (Exception ex) {
            LOG.warning("Connection failed........\n" + ex);
        }
        return null;
    }

}
