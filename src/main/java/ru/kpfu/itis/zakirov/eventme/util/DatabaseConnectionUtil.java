package ru.kpfu.itis.zakirov.eventme.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConnectionUtil {
    private static Connection connection;

    public static Connection getConnection() {


        String PROD_DB_HOST = System.getenv("PROD_DB_HOST");
        String PROD_DB_PORT = System.getenv("PROD_DB_PORT");
        String PROD_DB_PASSWORD = System.getenv("PROD_DB_PASSWORD");
        String PROD_DB_NAME = System.getenv("PROD_DB_NAME");
        String PROD_DB_USERNAME = System.getenv("PROD_DB_USERNAME");

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://%s:%s/%s"
                            .formatted(PROD_DB_HOST, PROD_DB_PORT, PROD_DB_NAME),
                    PROD_DB_USERNAME,
                    PROD_DB_PASSWORD
            );
        } catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }

        return connection;
    }

}