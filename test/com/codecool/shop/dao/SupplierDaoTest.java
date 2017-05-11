package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

abstract class SupplierDaoTest {


    static SupplierDao dao;

    @BeforeAll
    public static void setup(){}


    @Test
    public void testIsSupplierDaoSingleton(){
        List<Supplier> daoList=new ArrayList<>(dao.getAll());
        SupplierDao fakeDao=SupplierDaoMem.getInstance();
        List<Supplier> fakeList=new ArrayList<>(fakeDao.getAll());
        assertEquals(daoList,fakeList);
    }

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