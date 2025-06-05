package com.onlineordersystem.factory.product;

import com.onlineordersystem.model.product.*;

public class ProductFactory {
    public Product createProduct(String type, String name, double price) {
        switch (type.toLowerCase()) {
            case "electronic":
                return new ElectronicProduct(name, price);
            case "fashion":
                return new FashionProduct(name, price);
            default:
                throw new IllegalArgumentException("Unknown product type: " + type);
        }
    }
}