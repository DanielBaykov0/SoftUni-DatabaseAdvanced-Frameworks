import constants.Constants;

import java.sql.*;
import java.util.Scanner;

public class E04AddMinion {

    public static final String SELECT_TOWN =
            String.format("SELECT%n" +
                            "t.id%n" +
                            "FROM%n" +
                            "`%s` AS t%n" +
                            "WHERE t.name = ?",
                    Constants.TABLE_TOWNS);

    public static final String INSERT_TOWN =
            String.format("INSERT INTO%n" +
                            "`%s` (`name`, `country`)%n" +
                            "VALUES%n" +
                            "(?, 'NULL')",
                    Constants.TABLE_TOWNS);

    public static final String SELECT_VILLAIN =
            String.format("SELECT *%n" +
                            "FROM%n" +
                            "`%s` AS v%n" +
                            "WHERE%n" +
                            "v.name = ?",
                    Constants.TABLE_VILLAINS);

    public static final String INSERT_VILLAIN =
            String.format("INSERT INTO%n" +
                            "`%s` (`name`, `evilness_factor`)%n" +
                            "VALUES%n" +
                            "(?, 'evil')",
                    Constants.TABLE_VILLAINS);

    public static final String INSERT_MINION =
            String.format("INSERT INTO%n" +
                            "`%s` (`name`, `age`, `town_id`)%n" +
                            "VALUES%n" +
                            "(?, ?, ?)",
                    Constants.TABLE_MINIONS);

    public static final String GET_MINION_ID =
            String.format("SELECT%n" +
                            "m.id%n" +
                            "FROM%n" +
                            "`%s` AS m%n" +
                            "WHERE m.name = ?",
                    Constants.TABLE_MINIONS);

    public static final String INSERT_MINION_TO_VILLAIN =
            String.format("INSERT INTO%n" +
                            "`%s`%n" +
                            "(`minion_id`, `villain_id`)%n" +
                            "VALUES%n" +
                            "(?, ?)",
                    Constants.TABLE_MINIONS_VILLAINS);

    public static void main(String[] args) throws SQLException {

        getOutput();
    }

    public static void getOutput() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        String[] tokens = scanner.nextLine().split(Constants.INPUT_SEPARATOR);
        String minionName = tokens[1];
        int minionAge = Integer.parseInt(tokens[2]);
        String minionTown = tokens[3];
        String villainName = scanner.nextLine().split(Constants.INPUT_SEPARATOR)[1];

        try (Connection connection = DriverManager.getConnection(Constants.URL_DATABASE);
             PreparedStatement selectTownStatement = connection.prepareStatement(SELECT_TOWN, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             PreparedStatement insertTownStatement = connection.prepareStatement(INSERT_TOWN, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             PreparedStatement selectVillainStatement = connection.prepareStatement(SELECT_VILLAIN, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             PreparedStatement insertVillainStatement = connection.prepareStatement(INSERT_VILLAIN, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             PreparedStatement insertMinionStatement = connection.prepareStatement(INSERT_MINION, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             PreparedStatement getMinionIdStatement = connection.prepareStatement(GET_MINION_ID, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             PreparedStatement insertMinionToVillainStatement = connection.prepareStatement(INSERT_MINION_TO_VILLAIN, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {

            connection.setAutoCommit(false);

            try {
                selectTownStatement.setString(1, minionTown);

                ResultSet town = selectTownStatement.executeQuery();
                if (!town.isBeforeFirst()) {
                    insertTownStatement.setString(1, minionTown);
                    insertTownStatement.executeUpdate();

                    System.out.printf((Constants.TOWN_ADDED) + "%n", minionTown);
                }

                town = selectTownStatement.executeQuery();
                town.first();
                int townId = town.getInt(Constants.ID);

                selectVillainStatement.setString(1, villainName);

                ResultSet villain = selectVillainStatement.executeQuery();
                if (!villain.isBeforeFirst()) {
                    insertVillainStatement.setString(1, villainName);
                    insertVillainStatement.executeUpdate();

                    System.out.printf((Constants.VILLAIN_ADDED) + "%n", villainName);
                }

                villain = selectVillainStatement.executeQuery();
                villain.first();
                int villainId = villain.getInt(Constants.ID);

                // insert minion
                insertMinionStatement.setString(1, minionName);
                insertMinionStatement.setInt(2, minionAge);
                insertMinionStatement.setInt(3, townId);
                insertMinionStatement.executeUpdate();

                // get minion ID
                getMinionIdStatement.setString(1, minionName);

                ResultSet minion = getMinionIdStatement.executeQuery();
                minion.first();
                int minionId = minion.getInt(Constants.ID);

                // insert minion to villain
                insertMinionToVillainStatement.setInt(1, minionId);
                insertMinionToVillainStatement.setInt(2, villainId);
                insertMinionToVillainStatement.executeUpdate();

                System.out.printf((Constants.ADDED_MINION_TO_VILLAIN) + "%n", minionName, villainName);

                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new SQLException(e.getCause());
        }
    }
}
