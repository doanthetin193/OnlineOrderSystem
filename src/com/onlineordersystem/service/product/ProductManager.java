package com.onlineordersystem.service.product;

import com.onlineordersystem.core.database.DatabaseConnection;
import com.onlineordersystem.model.product.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    private List<Product> products = new ArrayList<>();
    private DatabaseConnection dbConnection;

    public ProductManager() {
        this.dbConnection = DatabaseConnection.getInstance();
    }

    public void addProduct(Product product) {
        products.add(product);
        dbConnection.executeQuery("INSERT INTO products (name, price) VALUES ('" + product.getName() + "', " + product.getPrice() + ")");
    }

    public void updateProduct(String name, Product updatedProduct) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equals(name)) {
                products.set(i, updatedProduct);
                dbConnection.executeQuery("UPDATE products SET name = '" + updatedProduct.getName() + "', price = " + updatedProduct.getPrice() + " WHERE name = '" + name + "'");
                return;
            }
        }
        throw new IllegalArgumentException("Product not found: " + name);
    }

    public void deleteProduct(String name) {
        products.removeIf(product -> product.getName().equals(name));
        dbConnection.executeQuery("DELETE FROM products WHERE name = '" + name + "'");
    }

    public List<Product> listProducts() {
        dbConnection.executeQuery("SELECT * FROM products");
        return new ArrayList<>(products);
    }
}