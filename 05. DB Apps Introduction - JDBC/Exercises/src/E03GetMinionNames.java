import constants.Constants;

import java.sql.*;
import java.util.Scanner;

public class E03GetMinionNames {

    public static final String GET_VILLAIN_NAME_FROM_ID =
            String.format("SELECT%n" +
                            "v.name%n" +
                            "FROM%n" +
                            "%s AS v%n" +
                            "WHERE%n" +
                            "v.id = ?",
                    Constants.TABLE_VILLAINS);

    public static final String SELECT_MINIONS_NAMES_AGE =
            String.format("SELECT%n" +
                            "m.name, m.age%n" +
                            "FROM%n" +
                            "%s AS v%n" +
                            "JOIN%n" +
                            "%s AS mv ON v.id = mv.villain_id%n" +
                            "JOIN%n" +
                            "%s AS m ON mv.minion_id = m.id%n" +
                            "WHERE%n" +
                            "v.id = ?",
                    Constants.TABLE_VILLAINS, Constants.TABLE_MINIONS_VILLAINS, Constants.TABLE_MINIONS);

    public static void main(String[] args) throws SQLException {

        getSelectMinionsNamesAge();
    }

    private static void getSelectMinionsNamesAge() throws SQLException {

        System.out.print(Constants.ENTER_VILLAIN_ID);
        Scanner scanner = new Scanner(System.in);
        int villainID = Integer.parseInt(scanner.nextLine());

        try (Connection connection = DriverManager.getConnection(Constants.URL_DATABASE)) {
            try (PreparedStatement psVillain = connection.prepareStatement(GET_VILLAIN_NAME_FROM_ID);
                 PreparedStatement psMinions = connection.prepareStatement(SELECT_MINIONS_NAMES_AGE)) {

                psVillain.setInt(1, villainID);
                psMinions.setInt(1, villainID);

                try (ResultSet rsVillain = psVillain.executeQuery();
                     ResultSet rsMinions = psMinions.executeQuery()) {

                    if (!rsVillain.isBeforeFirst()) {
                        System.out.printf((Constants.INVALID_VILLAIN_ID) + "%n", villainID);
                    } else {
                        while (rsVillain.next()) {
                            System.out.println(Constants.VILLAIN + rsVillain.getString(Constants.NAME));
                        }
                    }

                    if (!rsMinions.isBeforeFirst()) {
                        System.out.println(Constants.NO_MINIONS);
                    } else {
                        int index = 1;
                        while (rsMinions.next()) {
                            System.out.println(index++ + "." + rsMinions.getString(Constants.NAME) + " " +
                                    rsMinions.getString(Constants.AGE));
                        }
                    }
                } catch (SQLException e) {
                    throw new SQLException(e.getCause());
                }
            }
        }
    }
}
