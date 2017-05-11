package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.DBConnection;

import java.sql.*;


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
}

