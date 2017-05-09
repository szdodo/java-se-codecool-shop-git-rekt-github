package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbc {

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/robertgaspar";
    private static final String DB_USER = "robertgaspar";
    private static final String DB_PASSWORD = DBPassword.password;
    private static ArrayList<Product> products = new ArrayList<>();
    private static ArrayList<Supplier> suppliers = new ArrayList<>();
    private static ProductDaoJdbc instance = null;

    private ProductDaoJdbc() {
    }

    public static ProductDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ProductDaoJdbc();
        }
        return instance;
    }

//    @Override
    public ArrayList<Supplier> getSuppliers() {

        String query = "SELECT * FROM supplier JOIN product ON supplier_id = supplier.id JOIN category ON category_id = category.id;";

        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)){
            while (resultSet.next()){
                System.out.println(resultSet.getString("supplier_id"));
                Supplier supplier= new Supplier(Integer.parseInt(resultSet.getString("id")), resultSet.getString("name"), "asdasd");
                suppliers.add(supplier);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }



        return suppliers;
    }


//    @Override
//    public Todo find(String id) {
//
//        String query = "SELECT * FROM todos WHERE id ='" + id + "';";
//
//        try (Connection connection = getConnection();
//             Statement statement =connection.createStatement();
//             ResultSet resultSet = statement.executeQuery(query);
//        ){
//            if (resultSet.next()){
//                Todo result = new Todo(resultSet.getString("title"),
//                        resultSet.getString("id"),
//                        Status.valueOf(resultSet.getString("status")));
//                return result;
//            } else {
//                return null;
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }

//    @Override
//    public void update(String id, String title) {
//        String query = "UPDATE todos SET title = '" + title + "' WHERE id = '" + id + "';";
//        executeQuery(query);
//    }

    // package private so test can see it, but TodoList not
    void deleteAll() {
        String query = "DELETE FROM todos;";
        executeQuery(query);
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
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
