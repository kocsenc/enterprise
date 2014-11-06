package utility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DBUtility {
 private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try {
             Properties prop = new Properties();
                //InputStream inputStream = DBUtility.class.getClassLoader().getResourceAsStream("resources/application.properties");
                //prop.load(inputStream);
                //String driver = prop.getProperty("driver");
                String driver = "com.mysql.jdbc.Driver";
                //String url = prop.getProperty("url");
                String url = "jdbc:mysql://129.21.208.134:3306/test";
                //String user = prop.getProperty("user");
                String user = "paybook";
                //String password = prop.getProperty("password");
                String password = "3wNFDeGwwUXv5F5";
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            //} catch (FileNotFoundException e) {
            //    e.printStackTrace();
            //} catch (IOException e) {
            //    e.printStackTrace();
            }
            return connection;
        }

    }

}