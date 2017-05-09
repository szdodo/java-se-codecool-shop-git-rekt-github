package com.codecool.shop.dao.implementation;


import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;

public class SupplierDaoJdbc extends DBConnection{

    private static ArrayList<Supplier> suppliers = new ArrayList<>();
    private static SupplierDaoJdbc instance = null;

    private SupplierDaoJdbc() {
    }

    public static SupplierDaoJdbc getInstance() {
        if (instance == null) {
            instance = new SupplierDaoJdbc();
        }
        return instance;
    }

    //    @Override
    public ArrayList<Supplier> generateSuppliers() {

        String query = "SELECT * FROM supplier;";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)){
            while (resultSet.next()){
                Supplier supplier= new Supplier(Integer.parseInt(resultSet.getString("id")), resultSet.getString("name"), "asdasd");
                suppliers.add(supplier);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return suppliers;
    }

    public static Supplier getSupplier(String id) {
        Supplier result = null;
        for (Supplier supplier : suppliers) {
            if (supplier.getId() == Integer.parseInt(id)) {
                result = supplier;
            }
        }
        return result;
    }
}
