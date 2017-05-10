package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryDaoTest {


    private static ProductCategoryDao dao;

    @BeforeAll
    public static void setup(){
        dao= ProductCategoryDaoMem.getInstance();
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        dao.add(tablet);
        ProductCategory laptop = new ProductCategory("Laptop", "Hardware", "Portable computers used for a variety of purposes.");
        dao.add(laptop);
    }

    @Test
    public void testIsProductCategoryDaoSingleton(){
        List<ProductCategory> daoList=new ArrayList<>(dao.getAll());
        ProductCategoryDao fakeDao=ProductCategoryDaoMem.getInstance();
        List<ProductCategory> fakeList=new ArrayList<>(fakeDao.getAll());
        assertEquals(daoList,fakeList);
    }

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
    public void testIsGetAllWorking(){
        assertEquals(2,dao.getAll().size());
    }

}