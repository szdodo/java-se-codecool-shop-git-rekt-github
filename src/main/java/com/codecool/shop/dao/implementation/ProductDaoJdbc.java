package com.codecool.shop.dao.implementation;


import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;

public class ProductDaoJdbc extends DBConnection{

    private static ArrayList<Product> products = new ArrayList<>();
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
    public ArrayList<Product> generateProducts() {
        products.clear();

        String query = "SELECT * FROM product;";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)){

            while (resultSet.next()){
                String supplierId = resultSet.getString("supplier_id");
                String categoryId = resultSet.getString("category_id");
                Supplier tempSup = SupplierDaoJdbc.getSupplier(supplierId);
                ProductCategory tempCateg = ProductCategoryDaoJdbc.getCategory(categoryId);
                Product product = new Product(Integer.parseInt(resultSet.getString("id")), resultSet.getString("name"), resultSet.getFloat("defaultPrice"), resultSet.getString("defaultCurrency"), resultSet.getString("description"), tempCateg, tempSup);
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }
}
