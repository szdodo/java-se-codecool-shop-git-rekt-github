package com.codecool.shop.model;

import java.util.Currency;

public class LineItem
{

    public String name;
    public float defaultPrice;
    public Currency defaultCurrency;


    public LineItem(Product product){
        this.name=product.name;
        this.defaultPrice=product.getDefaultPrice();
        this.defaultCurrency=product.getDefaultCurrency();
    }

    public LineItem(String name, float defaultPrice, Currency defaultCurrency){
        this.name=name;
        this.defaultCurrency=defaultCurrency;
        this.defaultPrice=defaultPrice;
    }

    public String toString() {
        return String.format("name: %1$s, " +
                        "defaulPrice: %2$s, "
                + "defaultCurrency: %3$s",

                this.name,
                this.defaultPrice,
                this.defaultCurrency
        );
    }
}
