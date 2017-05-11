package com.codecool.shop.dao;


import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductDaoMemTestSet extends ProductDaoTest{

    @BeforeAll
    public static void setup(){
        dao= ProductDaoMem.getInstance();
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        Supplier lenovo = new Supplier("Lenovo", "Computers and laptops");
        dao.add(new Product("Amazon Fire HD 8", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls.", tablet, amazon));
        dao.add(new Product("Lenovo IdeaPad", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports.", tablet, lenovo));
    }

    @Override
    @Test
    public void testIsProductDaoSingleton(){
        List<Product> daoList=new ArrayList<>(dao.getAll());
        ProductDao fakeDao=ProductDaoMem.getInstance();
        List<Product> fakeList=new ArrayList<>(fakeDao.getAll());
        assertEquals(daoList,fakeList);
    }

    @Override
    @Test
    public void testIsFindWorking(){
        Product found=dao.find(1);
        assertEquals("Amazon Fire HD 8",found.getName());
    }

    @Override
    @Test
    public void testIsGetAllWorking(){
        assertEquals(1,dao.getAll().size());
    }

}
