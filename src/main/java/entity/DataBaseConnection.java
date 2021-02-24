package entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Silauras
 * Created on 18.02.2021 at 18:20
 */
public abstract class DataBaseConnection {
    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/epos?serverTimezone=Europe/Kiev",
                    "root",
                    "root"
            );
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public static java.sql.Connection getConnetion() throws SQLException {
        return connection;
    }
    public static void connectionClose(){
        try {
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
