package com.onlineordersystem.model.product;

public class ElectronicProduct extends Product {
    public ElectronicProduct(String name, double price) {
        super(name, price);
    }

    @Override
    public String getDescription() {
        return "Electronic Product: " + name + ", Price: $" + price;
    }
}