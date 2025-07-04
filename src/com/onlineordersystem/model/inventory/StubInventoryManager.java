package com.onlineordersystem.model.inventory;

import com.onlineordersystem.core.database.DatabaseConnection;
import com.onlineordersystem.model.product.Product;

import java.util.HashMap;
import java.util.Map;

public class StubInventoryManager implements InventoryManager {
    private Map<String, Integer> inventory = new HashMap<>();
    private DatabaseConnection dbConnection;

    public StubInventoryManager() {
        this.dbConnection = DatabaseConnection.getInstance();
    }

    public void addProduct(Product product, int quantity) {
        inventory.put(product.getName(), quantity);
        dbConnection.executeQuery("INSERT INTO inventory (name, quantity) VALUES ('" + product.getName() + "', " + quantity + ")");
    }

    public boolean checkAvailability(Product product, int quantity) {
        Integer available = inventory.getOrDefault(product.getName(), 0);
        return available >= quantity;
    }

    public void updateInventory(Product product, int quantity) {
        if (!checkAvailability(product, quantity)) {
            throw new IllegalArgumentException("Not enough stock for " + product.getName());
        }
        inventory.put(product.getName(), inventory.get(product.getName()) - quantity);
        dbConnection.executeQuery("UPDATE inventory SET quantity = " + inventory.get(product.getName()) + " WHERE name = '" + product.getName() + "'");
    }
}