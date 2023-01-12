import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class E01ConnectionAndProperties {

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your username: ");
        String user = scanner.nextLine();
        user = user.equals("") ? "root" : user;
        System.out.println();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine().trim();
        System.out.println();

        Properties properties = new Properties();
        properties.setProperty("user", user);
        properties.setProperty("password", password);

        Connection connection =
                DriverManager.getConnection("jdbc:mysql://localhost:3306/soft_uni", properties);

        PreparedStatement preparedStatement =
                connection.prepareStatement("SELECT * FROM soft_uni.employees WHERE employees.salary > ? LIMIT 5");

        System.out.print("Enter salary: ");
        String salary = scanner.nextLine();
        preparedStatement.setDouble(1, Double.parseDouble(salary));
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            System.out.println(resultSet.getString("first_name") + " " + resultSet.getString("last_name"));
        }
        connection.close();
    }
}
