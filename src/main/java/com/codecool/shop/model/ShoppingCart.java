package com.codecool.shop.model;

import javax.sound.sampled.Line;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class ShoppingCart {

    private HashMap<String, ArrayList> cartContent = new HashMap<>();
    private static ShoppingCart instance = null;
    private Float totalPrice=(float)0.0;
    private static Integer cartSize=0;


    protected ShoppingCart() {
    }

    public static ShoppingCart getInstance() {
        if (instance == null) {
            instance = new ShoppingCart();
        }
        return instance;
    }


    public void addToCart(Product product) {
        cartSize+=1;
        LineItem newItem = new LineItem(product);
        ArrayList<LineItem> items = new ArrayList<>();
        if (cartContent.size()==0){
            items.add(new LineItem(product));
            cartContent.put("products", items);
        }else {
            items = cartContent.get("products");
            boolean exist=false;
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).name.equals(newItem.name)){
                    items.get(i).quantity+=1;
                    exist=true;
                }
            }
            if(!exist) {
                items.add(newItem);
            }

        }
        addPriceToTotal(newItem);
    }


    public void updateCart(String name, Integer quantity){
        ArrayList<LineItem> items = cartContent.get("products");
        for (int i=0;i<items.size();i++){
            if (items.get(i).name.equals(name)){
                if(quantity==0) {
                    cartSize-=items.get(i).quantity;
                    removePriceFromTotal(items.get(i).quantity, items.get(i).defaultPrice);
                    items.remove(i);

                }else{
                    Integer change = items.get(i).quantity-quantity;
                    cartSize -= change;
                    items.get(i).quantity = quantity;
                }
            }
        }
    }


    public HashMap<String, ArrayList> getCartContent() {
        return cartContent;
    }

    public void emptyCart(){
        cartContent.clear();
    }

    protected void removePriceFromTotal(Integer quantity, Float price){
        Float change=quantity*price;
        totalPrice-=change;
    }

    protected void addPriceToTotal(LineItem newItem){
        totalPrice+=newItem.defaultPrice;
    }

    public String getTotalPrice(){
        return this.totalPrice.toString();
    }

    public String getCartSize(){
        return cartSize.toString();
    }

}
