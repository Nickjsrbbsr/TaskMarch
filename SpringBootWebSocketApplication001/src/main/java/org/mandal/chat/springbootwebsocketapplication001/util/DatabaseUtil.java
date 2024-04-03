package org.mandal.chat.springbootwebsocketapplication001.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/greetings_db";
    private static final String USERNAME = "nihar";
    private static final String PASSWORD = "mandal";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
