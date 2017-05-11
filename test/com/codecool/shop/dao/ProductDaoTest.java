package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

abstract class ProductDaoTest {

    static ProductDao dao = null;

    @BeforeAll
    public static void setup() {
    }

    @Test
    abstract public void testIsProductDaoSingleton();


    @Test
    abstract public void testIsFindWorking();

    @Test
    public void testRemoveRemovingElements() {
        int daoSize = dao.getAll().size();
        dao.remove(2);
        int newDaoSize = dao.getAll().size();
        assertEquals(daoSize - 1, newDaoSize);
    }

    @Test
    abstract public void testIsGetAllWorking();


}