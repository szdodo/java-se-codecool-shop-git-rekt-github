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

    static ProductDao dao;

    @BeforeAll
    public static void setup(){}

    @Test
    public void testIsSupplierDaoSingleton(){
        List<Product> daoList=new ArrayList<>(dao.getAll());
        ProductDao fakeDao=ProductDaoMem.getInstance();
        List<Product> fakeList=new ArrayList<>(fakeDao.getAll());
        assertEquals(daoList,fakeList);
    }

    @Test
    public void testIsAddAddingElements(){
        int daoSize=dao.getAll().size();
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        dao.add(new Product("Amazon Fire HD 8", 220, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        int newDaoSize=dao.getAll().size();
        assertEquals(daoSize+1, newDaoSize);
    }

    @Test
    public void testIsFindWorking(){
        Product found=dao.find(1);
        assertEquals("Amazon Fire HD 8",found.getName());
    }

    @Test
    public void testRemoveRemovingElements(){
        int daoSize=dao.getAll().size();
        dao.remove(2);
        int newDaoSize=dao.getAll().size();
        assertEquals(daoSize-1, newDaoSize);
    }

    @Test
    public void testIsGetAllWorking(){
        assertEquals(2,dao.getAll().size());
    }




}