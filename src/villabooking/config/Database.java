package villabooking.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static Connection connection;
    private static final String DB_URL = "jdbc:sqlite:C:/sqlite/villa_booking.db";

    // Return existing connection or create new one
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DB_URL);
            System.out.println("✅ Connected to SQLite database at: " + DB_URL);
        }
        return connection;
    }

    // Optional: close connection safely
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("🔒 Connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
