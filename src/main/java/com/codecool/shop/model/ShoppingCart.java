package com.codecool.shop.model;

import java.util.ArrayList;

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

    public void addToCart(LineItem lineItem){ cartContent.add(lineItem); }

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
