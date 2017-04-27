package com.codecool.shop.model;


public class Order {

    private String name;
    private String email;
    private Integer phoneNumber;
    private String billingAddress; //country,city,zipcode,address
    private String shippingAddress;
    private ShoppingCart cart;
    private boolean paid;
    private static Order instance = null;


    public void addPersonalInfo(ShoppingCart cart){
        this.cart=cart;
    }

    protected Order(String name, String email, Integer phoneNumber, String billingAddress, String shippingAddress){
        this.name=name;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.billingAddress=billingAddress;
        this.shippingAddress=shippingAddress;
        this.paid=false;
    }


    public void updateCart(ShoppingCart cart){
        this.cart.emptyCart();
        this.cart=cart;

    }


    public static Order getInstance(String name, String email, Integer phoneNumber, String billingAddress, String shippingAddress) {
        if (instance == null) {
            instance = new Order(name, email, phoneNumber, billingAddress, shippingAddress);
        }
        return instance;
    }

}
