package com.codecool.shop.dao;


import com.codecool.shop.dao.implementation.DBPassword;
import com.codecool.shop.dao.implementation.ProductDaoJdbc;
import com.codecool.shop.model.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ProductDaoJdbcTestSet extends ProductDaoTest {

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private static final String DB_USER = DBPassword.readFile().get(0);
    private static final String DB_PASSWORD = DBPassword.readFile().get(1);


    @BeforeAll
    public static void setup() {
        try {
            Connection con = DriverManager.getConnection(
                    DATABASE,
                    DB_USER,
                    DB_PASSWORD);
            ScriptRunner runner = new ScriptRunner(con, false, true);
            runner.runScript(new BufferedReader(new FileReader("src/main/resources/public/sql/init_db.sql")));
            runner.runScript(new BufferedReader(new FileReader("src/main/resources/public/sql/create_data.sql")));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dao = ProductDaoJdbc.getInstance();
        dao.getAll();
    }

    @Override
    @Test
    public void testIsProductDaoSingleton() {
        ProductDao fakeDao = ProductDaoJdbc.getInstance();
        assertEquals(dao.hashCode(), fakeDao.hashCode());
    }

    @Override
    @Test
    public void testIsFindWorking() {
        Product found = dao.find(1);
        assertEquals("Amazon Fire", found.getName());
    }

    @Override
    @Test
    public void testIsGetAllWorking() {
        assertEquals(17, dao.getAll().size());
    }


}
