package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

abstract class SupplierDaoTest {


    static SupplierDao dao=null;

    @BeforeAll
    public static void setup(){}


    @Test
    abstract public void testIsSupplierDaoSingleton();

    @Test
    public void testIsAddAddingElements(){
        int daoSize=dao.getAll().size();
        dao.add(new Supplier("HTC", "Mobile phones and accessories"));
        int newDaoSize=dao.getAll().size();
        assertEquals(daoSize+1, newDaoSize);
    }

    @Test
    public void testIsFindWorking(){
        Supplier found=dao.find(1);
        assertEquals("Amazon",found.getName());
    }


    @Test
    abstract public void testIsGetAllWorking();

}