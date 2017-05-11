package com.codecool.shop.dao;


import com.codecool.shop.dao.implementation.DBConnection;
import com.codecool.shop.dao.implementation.DBPassword;
import com.codecool.shop.dao.implementation.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ProductCategoryJdbcTestSet extends ProductCategoryDaoTest {

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private static final String DB_USER = DBPassword.readFile().get(0);
    private static final String DB_PASSWORD = DBPassword.readFile().get(1);

    @BeforeAll
    public static void setup() {
        dao = ProductCategoryDaoJdbc.getInstance();
        dao.getAll();
    }

    @Override
    @Test
    public void testIsProductCategoryDaoSingleton(){
        ProductCategoryDao fakeDao=ProductCategoryDaoJdbc.getInstance();
        assertEquals(dao.hashCode(),fakeDao.hashCode());
    }

    @Override
    @Test
    public void testIsGetAllWorking(){
        assertEquals(8,dao.getAll());
    }

}
