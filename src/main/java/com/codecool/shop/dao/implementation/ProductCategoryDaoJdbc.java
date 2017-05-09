package com.codecool.shop.dao.implementation;


import com.codecool.shop.model.ProductCategory;

import java.sql.*;
import java.util.ArrayList;

public class ProductCategoryDaoJdbc extends DBConnection{

    private static ArrayList<ProductCategory> categories = new ArrayList<>();
    private static ProductCategoryDaoJdbc instance = null;

    private ProductCategoryDaoJdbc() {
    }

    public static ProductCategoryDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoJdbc();
        }
        return instance;
    }

    // @Override
    public ArrayList<ProductCategory> generateProductCategories() {

        String query = "SELECT * FROM category;";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)){
            while (resultSet.next()){
                ProductCategory category = new ProductCategory(Integer.parseInt(resultSet.getString("id")), resultSet.getString("name"), resultSet.getString("department"), "asdasd");
                categories.add(category);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    public static ProductCategory getCategory(String id) {
        ProductCategory result = null;
        for (ProductCategory category : categories) {
            if (category.getId() == Integer.parseInt(id)) {
                result = category;
            }
        }
        return result;
    }
}