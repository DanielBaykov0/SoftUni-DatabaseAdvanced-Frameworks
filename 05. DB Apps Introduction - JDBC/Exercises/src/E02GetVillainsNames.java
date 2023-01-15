import constants.Constants;

import java.sql.*;

public class E02GetVillainsNames {

    public static final String SELECT_VILLAINS_NAMES_WITH_MORE_THAN_THREE_MINIONS =
            String.format("SELECT%n" +
                    "v.name, count(minion_id) AS `count`%n" +
                    "FROM %s AS v%n" +
                    "JOIN %s AS mv on v.id = mv.villain_id%n" +
                    "GROUP BY villain_id%n" +
                    "HAVING COUNT(minion_id) > 3%n" +
                    "ORDER BY `count` DESC", Constants.TABLE_VILLAINS, Constants.TABLE_MINIONS_VILLAINS);

    public static void main(String[] args) {

        getSelectVillainsNamesWithMoreThanThreeMinions();
    }

    private static void getSelectVillainsNamesWithMoreThanThreeMinions() {

        String result = null;
        try (Connection connection = DriverManager.getConnection(Constants.URL_DATABASE);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_VILLAINS_NAMES_WITH_MORE_THAN_THREE_MINIONS)) {

            while (resultSet.next()) {
                System.out.println(resultSet.getString("name") + " " + resultSet.getInt("count"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
