package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbc extends DBConnection implements ProductDao {

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

    @Override
    public ArrayList<Product> getAll() {
        products.clear();

        String query = "SELECT * FROM product;";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String supplierId = resultSet.getString("supplier_id");
                String categoryId = resultSet.getString("category_id");
                Supplier tempSup = SupplierDaoJdbc.getSupplier(supplierId);
                ProductCategoryDaoJdbc prodCat = ProductCategoryDaoJdbc.getInstance();
                ProductCategory tempCateg = prodCat.getCategory(Integer.parseInt(categoryId));
                Product product = new Product(Integer.parseInt(resultSet.getString("id")), resultSet.getString("name"), resultSet.getFloat("defaultPrice"), resultSet.getString("defaultCurrency"), resultSet.getString("description"), tempCateg, tempSup);
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }


    @Override
    public void add(Product product) {
        int newId = products.size() + 1;
        String query = "INSERT INTO product (id, name, defaultprice, defaultcurrency, " +
                "description, category_id, supplier_id)" +
                " VALUES (" + newId + ",'" + product.getName() + "','" + product.getDefaultPrice()
                + "','" + product.getDefaultCurrency() + "','" + product.getDescription() + "',"
                + product.getProductCategory().getId() + "," + product.getSupplier().getId() + ");";
        executeQuery(query);
    }

    @Override
    public Product find(int id) {
        String query="SELECT * FROM product WHERE id="+id+";";
        Product foundProduct=null;
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)){
            while (resultSet.next()){
                foundProduct = resultProduct(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundProduct;
    }

    @Override
    public void remove(int id) {
        String query="DELETE FROM product WHERE id="+ id +";";
        executeQuery(query);

    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        String query="SELECT * FROM product WHERE supplier_id="+supplier.getId()+";";
        List<Product> prod = getBy(query);
        return prod;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        String query="SELECT * FROM product WHERE category_id="+productCategory.getId()+";";
        List<Product> prod = getBy(query);
        return prod;
    }

    private List<Product> getBy(String query) {
        List<Product> prod = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)){
            while (resultSet.next()){
                Product foundProduct=resultProduct(resultSet);
                prod.add(foundProduct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prod;
    }


    private Product resultProduct(ResultSet resultSet) throws SQLException{
        String supplierId = resultSet.getString("supplier_id");
        String categoryId = resultSet.getString("category_id");
        Supplier tempSup = SupplierDaoJdbc.getSupplier(supplierId);
        ProductCategoryDaoJdbc prodCat = ProductCategoryDaoJdbc.getInstance();
        ProductCategory tempCateg = prodCat.getCategory(Integer.parseInt(categoryId));
        Product foundProduct = new Product(Integer.parseInt(resultSet.getString("id")),
                resultSet.getString("name"), resultSet.getFloat("defaultprice"),
                resultSet.getString("defaultcurrency"), resultSet.getString("description"),
                tempCateg, tempSup);
        return foundProduct;
    }

}
