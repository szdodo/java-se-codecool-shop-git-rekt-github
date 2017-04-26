package com.codecool.shop.model;

import java.util.ArrayList;

/**
 * Created by robertgaspar on 25/04/2017.
 */
public class ShoppingCart {

    private ArrayList<LineItem> cartContent = new ArrayList<>();

    private static ShoppingCart instance = null;

    protected ShoppingCart() {
    }

    public static ShoppingCart getInstance() {
        if (instance == null) {
            instance = new ShoppingCart();
        }
        return instance;
    }

    public void addToCart(Product product) {
        cartContent.add(new LineItem(product));
    }

    public ArrayList<LineItem> getCartContent() {
        return cartContent;
    }

    public void emptyCart(){
        cartContent.clear();
    }


}
