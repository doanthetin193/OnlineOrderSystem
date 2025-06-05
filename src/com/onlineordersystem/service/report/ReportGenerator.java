package com.onlineordersystem.service.report;

import com.onlineordersystem.core.database.DatabaseConnection;
import com.onlineordersystem.model.order.Order;
import com.onlineordersystem.model.product.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportGenerator {
    private DatabaseConnection dbConnection;

    public ReportGenerator() {
        this.dbConnection = DatabaseConnection.getInstance();
    }

    public double calculateRevenue(List<Order> orders) {
        double revenue = orders.stream().mapToDouble(Order::getDiscountedTotal).sum();
        dbConnection.executeQuery("SELECT SUM(total) FROM orders");
        return revenue;
    }

    public String findBestSellingProduct(List<Order> orders) {
        Map<String, Integer> productCount = new HashMap<>();
        for (Order order : orders) {
            for (Product product : order.getProducts()) {
                productCount.put(product.getName(), productCount.getOrDefault(product.getName(), 0) + 1);
            }
        }
        System.out.println("Product counts: " + productCount);
        String bestSeller = productCount.entrySet().stream()
                .sorted((e1, e2) -> {
                    int cmp = e2.getValue().compareTo(e1.getValue()); // Số lượng giảm dần
                    return cmp != 0 ? cmp : e1.getKey().compareTo(e2.getKey()); // Tên tăng dần
                })
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse("No products");
        dbConnection.executeQuery("SELECT name FROM products ORDER BY sales_count DESC LIMIT 1");
        return bestSeller;
    }
}