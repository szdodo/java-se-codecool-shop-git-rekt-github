package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import spark.Request;
import spark.Response;
import spark.ModelAndView;

import java.util.HashMap;
import java.util.Map;

public class ProductController {

    public static ModelAndView renderProductsByCategory(Request req, Response res, String category) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

        Map params = new HashMap<>();

        int catIdx = 0;
        switch (category) {
            case "tablet":
                catIdx = 1;
                break;
            case "laptop":
                catIdx = 2;
                break;
        }

        params.put("category", productCategoryDataStore.find(catIdx));
        params.put("products", productDataStore.getBy(productCategoryDataStore.find(catIdx)));
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderProductsBySupplier(Request req, Response res, String supplier) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        SupplierDao supplierDaoDataStore = SupplierDaoMem.getInstance();


        Map params = new HashMap<>();

        int supIdx = 0;
        switch (supplier) {
            case "Amazon":
                supIdx = 1;
                break;
            case "Lenovo":
                supIdx = 2;
                break;
        }

        params.put("category", supplierDaoDataStore.find(supIdx));
        params.put("products", productDataStore.getBy(supplierDaoDataStore.find(supIdx)));
        return new ModelAndView(params, "product/index");
    }

}
