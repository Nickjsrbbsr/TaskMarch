package org.mandal.chat.springbootwebsocketapplication001.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcExample {
    public static void main(Integer id,String name,String status) {
        String url = "jdbc:mysql://localhost:3306/greetings_db";
        String username = "nihar";
        String password = "mandal";

        String sql = "INSERT INTO `greetings_db`.`greetingdto` (`id`, `name`, `status`) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id); // Set id
            statement.setString(2, name); // Set name
            statement.setString(3, status); // Set status

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new record was inserted successfully!");
            } else {
                System.out.println("Failed to insert the record.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        main(101,"Nihar","Mandal");
    }
}

