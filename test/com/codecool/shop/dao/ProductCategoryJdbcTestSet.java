package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.*;

import com.codecool.shop.dbconnection.DBPassword;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductCategoryJdbcTestSet extends ProductCategoryDaoTest {

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
        dao = ProductCategoryDaoJdbc.getInstance();
        dao.getAll();
    }

    @Override
    @Test
    public void testIsProductCategoryDaoSingleton() {
        ProductCategoryDao fakeDao = ProductCategoryDaoJdbc.getInstance();
        assertEquals(dao.hashCode(), fakeDao.hashCode());
    }

    @Override
    @Test
    public void testIsGetAllWorking() {
        assertEquals(9, dao.getAll().size());
    }
}
