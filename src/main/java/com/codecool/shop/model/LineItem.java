package com.codecool.shop.model;

import java.util.Currency;

public class LineItem
{

    private String name;
    private float defaultPrice;
    private Currency defaultCurrency;


    public LineItem(Product product){
        this.name=product.name;
        this.defaultPrice=product.getDefaultPrice();
        this.defaultCurrency=product.getDefaultCurrency();
    }
}
