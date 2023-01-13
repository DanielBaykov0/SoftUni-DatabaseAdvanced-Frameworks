package constants;

public final class SQLInsertData {

    public static final String INSERT_INTO_TOWNS =
            String.format("INSERT INTO %s%n" +
                    "(name, country)%n" +
                    "VALUES%n" +
                    "(Sofia, Bulgaria),%n" +
                    "(Liverpool, England),%n" +
                    "(Berlin, Germany),%n" +
                    "(Eindhoven, Netherlands),%n" +
                    "(Madrid, Spain);", Constants.TABLE_TOWNS);

    public static final String INSERT_INTO_MINIONS =
            String.format("INSERT INTO %s%n" +
                    "(name, age, town_id)%n" +
                    "VALUES%n" +
                    "(Bob, 13, 1),%n" +
                    "(Kevin, 14, 2),%n" +
                    "(Steward, 19, 5),%n" +
                    "(Simon, 22, 4),%n" +
                    "(Victor, 16, 3);", Constants.TABLE_MINIONS);

    public static final String INSERT_INTO_VILLAINS =
            String.format("INSERT INTO %s%n" +
                    "(name, evilness_factor)%n" +
                    "VALUES%n" +
                    "(Gru, good),%n" +
                    "(Koko, evil),%n" +
                    "(Misho, bad),%n" +
                    "(Juji, bad),%n" +
                    "(Mani, super evil);", Constants.TABLE_VILLAINS);

    public static final String INSERT_INTO_MINIONS_VILLAINS =
            String.format("INSERT INTO %s%n" +
                    "VALUES%n" +
                    "(1, 1),%n" +
                    "(3, 1),%n" +
                    "(1, 2),%n" +
                    "(2, 2),%n" +
                    "(4, 5),%n" +
                    "(2, 1),%n" +
                    "(5, 5),%n" +
                    "(4, 1),%n" +
                    "(4, 2),%n" +
                    "(4, 3),%n" +
                    "(5, 1),%n" +
                    "(5, 2),%n" +
                    "(5, 3),%n" +
                    "(5, 4),%n" +
                    "(3, 3);", Constants.TABLE_MINIONS_VILLAINS);

    private SQLInsertData() {}
}
