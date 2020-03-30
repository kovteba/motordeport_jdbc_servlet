package ua.nure.kovteba.finaltask.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

public class Connect {

    private static Logger LOG = Logger.getLogger(Connect.class.getName());

    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/motordeport?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "root";

    //Create connection
    public static Connection connect() {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            LOG.info("Connection to Store DB succesfull!");
            return connection;
        } catch (Exception ex) {
            LOG.warning("Connection failed........\n" + ex);
        }
        return null;
    }

}
