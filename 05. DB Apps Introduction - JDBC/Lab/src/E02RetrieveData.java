import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class E02RetrieveData {

    public static final String sql = "SELECT u.first_name, u.last_name," + " COUNT(ug.id) " +
            "FROM diablo.users AS u JOIN diablo.users_games AS ug ON u.id = ug.user_id " +
            "WHERE u.user_name = ? GROUP BY u.id";

    public static void main(String[] args) throws SQLException {
        String user = "root";
        String password = "!Die69@32";

        Properties properties = new Properties();
        properties.setProperty("user", user);
        properties.setProperty("password", password);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter player name: ");
        String userName = scanner.nextLine().trim();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/diablo", properties);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                boolean found = false;

                while (resultSet.next()) {
                    found = true;
                    System.out.printf("User: %s%n%s%s has played %d games",
                            userName, resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getInt("COUNT(ug.id)"));
                }

                if (!found) {
                    System.out.println("No such user exists");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
