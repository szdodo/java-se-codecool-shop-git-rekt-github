package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJdbc extends DBConnection implements SupplierDao {

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

    @Override
    public ArrayList<Supplier> getAll() {
        suppliers.clear();
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

    @Override
    public void add(Supplier supplier){
        int newId=suppliers.size()+1;
        String query = "INSERT INTO supplier (id, name) VALUES (" +newId+",'" + supplier.getName() +"');";
        executeQuery(query);
    }

    @Override
    public Supplier find(int id){
        String query="SELECT * FROM supplier WHERE id="+id+";";
        Supplier foundSupplier=null;
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)){
            while (resultSet.next()){
                foundSupplier = new Supplier(Integer.parseInt(resultSet.getString("id")), resultSet.getString("name"),"");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundSupplier;

    }


}
