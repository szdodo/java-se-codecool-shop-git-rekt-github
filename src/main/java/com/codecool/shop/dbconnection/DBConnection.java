package com.codecool.shop.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DBConnection {

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private static final String DB_USER = DBPassword.readFile().get(0);
    private static final String DB_PASSWORD = DBPassword.readFile().get(1);
    private static final Logger logger = LoggerFactory.getLogger(DBConnection.class);

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

    protected void executeQuery(String query) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            logger.error("{} SQLException occured.", e);
            e.printStackTrace();
        }
    }
}
