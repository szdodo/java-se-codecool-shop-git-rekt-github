package com.codecool.shop.controller;

import com.codecool.shop.dbconnection.DBConnection;

import java.sql.*;
import java.util.UUID;

/**
 * The CustomerController class extends the DBConnection class to connect to the database.
 * The class runs sql queries to get the necessary information about the customer
 * for the Order class.
 */
public class CustomerController extends DBConnection {

    public Boolean loginValidation(String login, String pw) {
        String query = "SELECT username, password FROM customer WHERE username = ? AND password = ? ";

        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, login);
            statement.setString(2, pw);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                String username = result.getString("username");
                String password = result.getString("password");
                if (login.equals(username) && pw.equals(password)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getUserId(String userName) {
        String query = "SELECT user_id FROM customer WHERE username='" + userName + "';";

        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String name = resultSet.getString("user_id");
                return name;
            }
        } catch (
                SQLException e)
        {
            e.printStackTrace();
        }
        return "getUserId failed";
    }

    public void registerUser(String name, String email, String username, String password, String address) {
        String user_id = UUID.randomUUID().toString();

        String query = "INSERT INTO customer (user_id, name, email, username, password, billing_address, shipping_address) VALUES(?, ?, ?, ?, ?, ?, ?);";
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user_id);
            statement.setString(2, name);
            statement.setString(3, email);
            statement.setString(4, username);
            statement.setString(5, password);
            statement.setString(6, address);
            statement.setString(7, address);
            statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

