package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.ProductDaoJdbc;

import spark.Request;
import spark.Response;
import spark.ModelAndView;

import java.util.HashMap;
import java.util.List;

/**
 * The ProductController class extends the DBConnection class to connect to the database.
 * The class gets the necessary information from the memory for the Product class.
 */
public class ProductController {

    public static ModelAndView renderAllProducts(Request req, Response res) {
        ProductDaoJdbc productDataStore = ProductDaoJdbc.getInstance();
        HashMap<String, List> params = new HashMap<>();
        params.put("products", productDataStore.getAll());
        return new ModelAndView(params, "product/index");
    }
}
