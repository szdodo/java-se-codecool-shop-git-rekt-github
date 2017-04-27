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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductController {

    public static ModelAndView renderProductsByCategory(Request req, Response res, String category) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

        HashMap<String,ArrayList> params=new HashMap<>();
        ArrayList<HashMap> catList=new ArrayList<>();

        int catIdx = 0;
        switch (category) {
            case "tablet":
                catIdx = 1;
                break;
            case "laptop":
                catIdx = 2;
                break;
        }
        HashMap par = new HashMap<>();

        par.put("category", productCategoryDataStore.find(catIdx));
        par.put("products", productDataStore.getBy(productCategoryDataStore.find(catIdx)));
        catList.add(par);
        params.put("categories", catList);
        return new ModelAndView(params, "product/newindex");
    }

    public static ModelAndView renderProductsBySupplier(Request req, Response res, String supplier) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        SupplierDao supplierDaoDataStore = SupplierDaoMem.getInstance();


        HashMap<String,ArrayList> params=new HashMap<>();
        ArrayList<HashMap> suppList=new ArrayList<>();

        int supIdx = 0;
        switch (supplier) {
            case "Amazon":
                supIdx = 1;
                break;
            case "Lenovo":
                supIdx = 2;
                break;
        }
        HashMap par = new HashMap<>();
        par.put("category", supplierDaoDataStore.find(supIdx));
        par.put("products", productDataStore.getBy(supplierDaoDataStore.find(supIdx)));
        suppList.add(par);
        params.put("categories", suppList);
        return new ModelAndView(params, "product/newindex");
    }

    public static ModelAndView renderProducts(Request req, Response res) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

        HashMap<String,ArrayList> params=new HashMap<>();
        ArrayList<HashMap> prodList=new ArrayList<>();
        int productQuantity=productCategoryDataStore.getAll().size();

        for (int i=1;i<=productQuantity;i++){
            HashMap par = new HashMap<>();
            par.put("category", productCategoryDataStore.find(i));
            par.put("products", productDataStore.getBy(productCategoryDataStore.find(i)));
            prodList.add(par);
            System.out.println(par);
        }
        params.put("categories", prodList);
        System.out.println(params);
        return new ModelAndView(params, "product/newindex");
    }

    public static ModelAndView renderAllProducts(Request req, Response res) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        HashMap<String,List> params=new HashMap<>();
        params.put("products", productDataStore.getAll());
        System.out.println(params);
        return new ModelAndView(params, "product/newindex");
    }


}
