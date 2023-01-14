package constants;

public final class SQLCreateTables {

    public static final String DROP_DATABASE =
            String.format("DROP DATABASE IF EXISTS %s", Constants.DATABASE);

    public static final String CREATE_DATABASE =
            String.format("CREATE DATABASE %s", Constants.DATABASE);

    public static final String CREATE_TABLE_TOWNS =
            String.format(
                    "CREATE TABLE `%s` (%n" +
                            "`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,%n" +
                            "`name` VARCHAR(50) NOT NULL,%n" +
                            "`country` VARCHAR(50) NOT NULL%n" +
                            ")", Constants.TABLE_TOWNS);

    public static final String CREATE_TABLE_MINIONS =
            String.format(
                    "CREATE TABLE `%s` (%n" +
                            "`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,%n" +
                            "`name` VARCHAR(50) NOT NULL,%n" +
                            "`age` INT NOT NULL,%n" +
                            "`town_id` INT NOT NULL,%n" +
                            "CONSTRAINT `fk_minions_town` FOREIGN KEY (`town_id`)%n" +
                            "REFERENCES `%s` (`id`)%n" +
                            ")", Constants.TABLE_MINIONS, Constants.TABLE_TOWNS);

    public static final String CREATE_TABLE_VILLAINS =
            String.format(
                    "CREATE TABLE `%s` (%n" +
                            "`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,%n" +
                            "`name` VARCHAR(50) NOT NULL,%n" +
                            "`evilness_factor` ENUM('good', 'bad', 'evil', 'super evil') NOT NULL DEFAULT 'good'%n" +
                            ")", Constants.TABLE_VILLAINS);

    public static final String CREATE_TABLE_MINIONS_VILLAINS =
            String.format(
                    "CREATE TABLE `%s` (%n" +
                            "`minion_id` INT NOT NULL,%n" +
                            "`villain_id` INT NOT NULL,%n" +
                            "CONSTRAINT `fk_minions_villains_minion` FOREIGN KEY (`minion_id`)%n" +
                            "REFERENCES `%s` (`id`),%n" +
                            "CONSTRAINT `fk_minions_villains_villain` FOREIGN KEY (`villain_id`)%n" +
                            "REFERENCES `%s` (`id`),%n" +
                            "CONSTRAINT `pk_minions_villains` PRIMARY KEY (`minion_id`, `villain_id`)%n" +
                            ")", Constants.TABLE_MINIONS_VILLAINS, Constants.TABLE_MINIONS, Constants.TABLE_VILLAINS);

    private SQLCreateTables() {
    }
}
