package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CartController extends DBConnection {

    public void checkCartDB(String userId, Integer productID) {
        String query = "SELECT product_id FROM cart WHERE user_id='" + userId + "' AND product_id = "+ productID +";";
        try(Connection connection = getConnection()) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                Integer prodID = resultSet.getInt("product_id");
                System.out.println(prodID);
                updateCartDB(userId, productID);
            }
            else {
                addToCartDB(userId, productID);
            }

        } catch (
                SQLException e)

        {
            e.printStackTrace();
        }
    }

    public void updateCartDB(String userId, Integer productID) {
        String query = "UPDATE cart SET quantity = quantity + 1 WHERE user_id='" + userId + "' AND product_id ='"+ productID +"';";
        executeQuery(query);
        System.out.println("product: " + productID + " updated for user with user_id: " + userId);

    }

    public void addToCartDB(String userId, Integer prodID) {
        String query = "INSERT INTO cart(user_id, quantity, product_id) VALUES('" + userId + "',1,'"+ prodID +"');";
        executeQuery(query);
        System.out.println("product: " + prodID + " added for user with user_id: " + userId);

    }

    private void executeQuery(String query) {
        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
        ){
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
