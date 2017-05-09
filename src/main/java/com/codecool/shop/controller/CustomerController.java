package com.codecool.shop.controller;
import spark.ModelAndView;
import java.util.HashMap;


public class CustomerController {

    public ModelAndView loginValidation(String username, String password) {

        // validate user bcrypt etc..
        // get customer from database
        // set session attribute: request.session().attribute("currentUser", customer);
        // redirect to home page

        HashMap<String, String> params = new HashMap<>();
        params.put("asd", "dsa");

        return new ModelAndView(params, "product/index");
    }

}

