package com.onlineordersystem.model.order;

import com.onlineordersystem.core.interfaces.OrderObserver;
import com.onlineordersystem.core.interfaces.PaymentStrategy;
import com.onlineordersystem.model.inventory.InventoryManager;
import com.onlineordersystem.model.product.Product;
import com.onlineordersystem.service.promotion.DiscountManager;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Product> products = new ArrayList<>();
    private List<OrderObserver> observers = new ArrayList<>();
    private String status;
    private PaymentStrategy paymentStrategy;
    private InventoryManager inventoryManager;
    private DiscountManager discountManager;
    private double discountedTotal;

    public Order(InventoryManager inventoryManager, DiscountManager discountManager) {
        this.status = "Pending";
        this.inventoryManager = inventoryManager;
        this.discountManager = discountManager;
        this.discountedTotal = 0.0;
    }

    public void addProduct(Product product) {
        inventoryManager.checkAvailability(product, 1);
        inventoryManager.updateInventory(product, 1);
        products.add(product);
        notifyObservers();
    }

    public void setStatus(String status) {
        this.status = status;
        notifyObservers();
    }

    public String getStatus() {
        return status;
    }

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void processPayment() {
        double total = products.stream().mapToDouble(Product::getPrice).sum();
        processPayment(total);
    }

    public void processPayment(double total) {
        if (paymentStrategy != null) {
            paymentStrategy.pay(total);
            setStatus("Paid");
            this.discountedTotal = total; // Lưu số tiền thanh toán
        } else {
            System.out.println("No payment strategy selected.");
        }
    }

    public double applyDiscount(String code) {
        double total = getTotal();
        double discounted = discountManager.applyDiscount(total, code);
        this.discountedTotal = discounted; // Lưu số tiền sau giảm giá
        return discounted;
    }

    private void notifyObservers() {
        System.out.println("Notifying observers with status: " + status + " (Products: " + products.size() + ")");
        for (OrderObserver observer : new ArrayList<>(observers)) {
            observer.update(status + " (Products: " + products.size() + ")");
        }
    }

    public double getTotal() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

    public double getDiscountedTotal() {
        return discountedTotal;
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }
}