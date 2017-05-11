package com.codecool.shop.dao;


import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeAll;

public class SupplierDaoMemTestSet extends SupplierDaoTest {

    @BeforeAll
    public static void setup(){
        dao= SupplierDaoMem.getInstance();
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        dao.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers and laptops");
        dao.add(lenovo);
    }
}
