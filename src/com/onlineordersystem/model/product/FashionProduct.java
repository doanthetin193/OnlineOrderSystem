package com.onlineordersystem.model.product;

public class FashionProduct extends Product {
    public FashionProduct(String name, double price) {
        super(name, price);
    }

    @Override
    public String getDescription() {
        return "Fashion Product: " + name + ", Price: $" + price;
    }
}