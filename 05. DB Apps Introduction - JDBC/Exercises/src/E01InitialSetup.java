import constants.Constants;
import constants.SQLCreateTables;
import constants.SQLInsertData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class E01InitialSetup {

    public static void main(String[] args) {

        if (createMinionsDatabase()) {
            System.out.println(Constants.DATABASE_CREATED_SUCCESSFULLY);
        } else {
            System.out.println(Constants.ERROR_CREATING_DATABASE);
        }

        if (createTablesInMinionsDatabase()) {
            System.out.println(Constants.TABLES_CREATED_SUCCESSFULLY);
        } else {
            System.out.println(Constants.ERROR_CREATING_TABLES);
        }

        if (populateTablesInMinionsDatabase()) {
            System.out.println(Constants.DATA_INSERTED_INTO_TABLES);
        } else {
            System.out.println(Constants.ERROR_INSERTING_DATA_INTO_TABLES);
        }
    }

    private static void executeStatements(String Host, String... statements) throws SQLException {
        try (Connection connection = DriverManager.getConnection(Host)) {
            try (Statement statement = connection.createStatement()) {
                connection.setAutoCommit(false);

                for (String query : statements) {
                    statement.execute(query);
                }

                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new SQLException(e.getCause());
            }
        }
    }

    private static boolean createMinionsDatabase() {
        try {
            executeStatements(
                    Constants.URL_HOST,
                    SQLCreateTables.DROP_DATABASE,
                    SQLCreateTables.CREATE_DATABASE);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private static boolean createTablesInMinionsDatabase() {
        try {
            executeStatements(
                    Constants.URL_DATABASE,
                    SQLCreateTables.CREATE_TABLE_TOWNS,
                    SQLCreateTables.CREATE_TABLE_MINIONS,
                    SQLCreateTables.CREATE_TABLE_VILLAINS,
                    SQLCreateTables.CREATE_TABLE_MINIONS_VILLAINS);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private static boolean populateTablesInMinionsDatabase() {
        try {
            executeStatements(Constants.URL_DATABASE,
                    SQLInsertData.INSERT_INTO_TOWNS,
                    SQLInsertData.INSERT_INTO_MINIONS,
                    SQLInsertData.INSERT_INTO_VILLAINS,
                    SQLInsertData.INSERT_INTO_MINIONS_VILLAINS);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
