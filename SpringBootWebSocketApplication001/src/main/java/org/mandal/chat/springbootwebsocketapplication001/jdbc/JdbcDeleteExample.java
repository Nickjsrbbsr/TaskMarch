package org.mandal.chat.springbootwebsocketapplication001.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcDeleteExample {
    public static void main(Integer id) {
        String url = "jdbc:mysql://localhost:3306/greetings_db";
        String username = "nihar";
        String password = "mandal";

        String sql = "DELETE FROM `greetings_db`.`greetingdto` WHERE (`id` = ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id); // Set id to delete

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Record deleted successfully!");
            } else {
                System.out.println("No record found with the provided id.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
