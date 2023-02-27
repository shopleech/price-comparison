package com.shopleech.base.dal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Ahto Jalak
 * @since 25.01.2023
 */
public class JdbcConnectionFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcConnectionFactory.class);

    public static Connection customizedSqlConnection() {

        String url = "jdbc:postgresql://localhost/test";
        Properties props = new Properties();
        props.setProperty("user", "");
        props.setProperty("password", "");
        props.setProperty("ssl", "true");
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, props);
            LOGGER.info("url is {}", url);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

//   public static Connection testConnection() throws SQLException {
//      Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/test", "asd", "asd");
//      try {
//         connection.setAutoCommit(false);
//         PreparedStatement firstStatement = connection .prepareStatement("firstQuery");
//         firstStatement.executeUpdate();
//         PreparedStatement secondStatement = connection .prepareStatement("secondQuery");
//         secondStatement.executeUpdate();
//         connection.commit();
//      } catch (Exception e) {
//         connection.rollback();
//      }
//
//      return connection;
//   }


}
