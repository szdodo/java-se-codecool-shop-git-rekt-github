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

        try(Connection connection = getConnection()) {

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, login);
            statement.setString(2, pw);
            System.out.println("1");
            ResultSet result = statement.executeQuery();
            System.out.println(result);
            System.out.println("2");



            System.out.println(result);

            while (result.next()) {
                String username = result.getString("username");
                String password = result.getString("password");
                System.out.println("while....asd.a.sd.asd.a");
                if (login.equals(username) && pw.equals(password)) {
                    return true;
                }
                else {
                    System.out.println("else");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("ez bizony false");
        return false;
    }

    public String getUserId(String userName) {
        String query = "SELECT user_id FROM customer WHERE username='" + userName + "';";
        try(Connection connection = getConnection();) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String name = resultSet.getString("user_id");
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

