package com.codecool.shop.model;

import java.util.ArrayList;

/**
 * The ProductCategory class extends the BaseModel class with the category's
 * department and products.
 */
public class ProductCategory extends BaseModel {

    private String department;
    private ArrayList<Product> products;

    public ProductCategory(String name, String department, String description) {
        super(name);
        this.department = department;
        this.products = new ArrayList<>();
    }

    public ProductCategory(int id, String name, String department, String description) {
        super(name);
        this.id = id;
        this.department = department;
        this.products = new ArrayList<>();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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
        return String.format(
                "id: %1$d," +
                        "name: %2$s, " +
                        "department: %3$s, " +
                        "description: %4$s",
                this.id,
                this.name,
                this.department,
                this.description);
    }
}