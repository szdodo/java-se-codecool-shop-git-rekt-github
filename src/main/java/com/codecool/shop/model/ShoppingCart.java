package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.HashMap;

public class ShoppingCart {

    private HashMap<String, ArrayList> cartContent = new HashMap<>();
    private static ShoppingCart instance = null;
    private Float totalPrice = (float) 0.0;
    private static Integer cartSize = 0;


    protected ShoppingCart() {
    }

    public static ShoppingCart getInstance() {
        if (instance == null) {
            instance = new ShoppingCart();
        }
        return instance;
    }

    public void addToCart(Product product) {
        cartSize += 1;
        LineItem newItem = new LineItem(product);
        ArrayList<LineItem> items = new ArrayList<>();

        if (cartContent.size() == 0) {
            items.add(new LineItem(product));
            cartContent.put("products", items);
        } else {
            items = cartContent.get("products");
            boolean exist = false;
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).name.equals(newItem.name)) {
                    items.get(i).quantity += 1;
                    exist = true;
                }
            }
            if (!exist) {
                items.add(newItem);
            }
        }
        addPriceToTotal(newItem);
    }


    public void updateCart(String name, Integer quantity) {
        ArrayList<LineItem> items = cartContent.get("products");

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).name.equals(name)) {
                if (quantity == 0) {
                    cartSize -= items.get(i).quantity;
                    changePriceFromTotal(items.get(i).quantity, items.get(i).defaultPrice, false);
                    items.remove(i);
                } else {
                    Integer change = items.get(i).quantity - quantity;
                    changePriceFromTotal(items.get(i).quantity, items.get(i).defaultPrice, false);
                    cartSize -= change;
                    items.get(i).quantity = quantity;
                    changePriceFromTotal(items.get(i).quantity, items.get(i).defaultPrice, true);
                }
            }
        }
    }

    public HashMap<String, ArrayList> getCartContent() {
        return cartContent;
    }

    void emptyCart() {
        cartContent.clear();
    }

    protected void changePriceFromTotal(Integer quantity, Float price, boolean positive) {
        Float change = quantity * price;
        if (positive) {
            totalPrice += change;
        } else {
            totalPrice -= change;
        }
    }

    protected void addPriceToTotal(LineItem newItem) {
        totalPrice += newItem.defaultPrice;
    }

    public String getTotalPrice() {
        return this.totalPrice.toString();
    }

    public String getCartSize() {
        return cartSize.toString();
    }
}
