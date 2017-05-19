package com.codecool.shop.model;

import java.util.ArrayList;

/**
 * TheSupplier class extends the BaseModel class with the supplier's description
 * and products.
 */
public class Supplier extends BaseModel {
    private ArrayList<Product> products;

    public Supplier(String name, String description) {
        super(name);
        this.description = description;
        this.products = new ArrayList<>();
    }

    public Supplier(int id, String name, String description) {
        super(id, name, description);
        this.products = new ArrayList<>();
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList getProducts() {
        return this.products;
    }

    void addProduct(Product product) {
        this.products.add(product);
    }

    public int getId(){
        return this.id;
    }

    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "description: %3$s",
                this.id,
                this.name,
                this.description
        );
    }
}