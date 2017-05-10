package com.codecool.shop.controller;
import com.codecool.shop.dao.implementation.DBConnection;

import java.sql.*;


public class CustomerController extends DBConnection {

    public Boolean loginValidation(String login, String pw) {

        // validate user bcrypt etc..
        // get customer from database
        // set session attribute: request.session().attribute("currentUser", customer);
        // redirect to home page
        String query = "SELECT username, password FROM customer WHERE username = ? AND password = ? ";

        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, login);
            statement.setString(2, pw);
            ResultSet result = statement.executeQuery();
            if (login.equals(result.getString("username")) && pw.equals(result.getString("password"))) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("ez bizony false");
        return false;
    }

    public String getUserId(String username) {
        String query = "SELECT name FROM customer WHERE username=" + username + ";";
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                System.out.println("sikerült! " + name);
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

