package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.DBConnection;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
abstract class ProductCategoryDaoTest extends DBConnection {


    static ProductCategoryDao dao=null;

    @BeforeAll
    public static void setup(){}

    @Test
    abstract public void testIsProductCategoryDaoSingleton();

    @Test
    public void testIsAddAddingElements(){
        int daoSize=dao.getAll().size();
        dao.add(new ProductCategory("Mobile", "Hardware", "Portable telephones"));
        int newDaoSize=dao.getAll().size();
        assertEquals(daoSize+1, newDaoSize);
    }

    @Test
    public void testIsFindWorking(){
        ProductCategory found=dao.find(1);
        assertEquals("Tablet",found.getName());
    }

    @Test
    public void testRemoveRemovingElements(){
        int daoSize=dao.getAll().size();
        dao.remove(2);
        int newDaoSize=dao.getAll().size();
        assertEquals(daoSize-1, newDaoSize);
    }

    @Test
    abstract public void testIsGetAllWorking();

}