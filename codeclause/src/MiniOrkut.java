import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MiniOrkut {
    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mini_orkut";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        try {
            // Connect to the database
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // User registration example
            registerUser(connection, "john_doe", "password123");

            // User login example
            boolean loginSuccessful = loginUser(connection, "john_doe", "password123");
            if (loginSuccessful) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Login failed.");
            }

            // Close the database connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void registerUser(Connection connection, String username, String password) throws SQLException {
        // Insert user into the database
        String insertQuery = "INSERT INTO users (username, password) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public static boolean loginUser(Connection connection, String username, String password) throws SQLException {
        // Check if the user exists and the password matches
        String selectQuery = "SELECT * FROM user WHERE name = ? AND password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();

        boolean loginSuccessful = resultSet.next(); // Check if any rows were returned

        preparedStatement.close();
        resultSet.close();

        return loginSuccessful;
    }
}
