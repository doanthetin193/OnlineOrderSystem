package com.onlineordersystem.model.order;

import java.util.ArrayList;
import java.util.List;

import com.onlineordersystem.core.interfaces.PaymentStrategy;
import com.onlineordersystem.model.inventory.InventoryManager;
import com.onlineordersystem.model.product.Product;
import com.onlineordersystem.service.promotion.DiscountManager;

public class Order implements Notifiable {
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
        notifyObservers(createProductAddedNotification(product));
    }

    public void setStatus(String status) {
        this.status = status;
        
        notifyObservers(createStatusChangedNotification());
    }

    public String getStatus() {
        return status;
    }

    public void addObserver(OrderObserver observer) {
        if (!observers.contains(observer)) {
        	observers.add(observer);
        }
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
            this.discountedTotal = total; 
        } else {
            System.out.println("No payment strategy selected.");
        }
    }

    public double applyDiscount(String code) {
        double total = getTotal();
        double discounted = discountManager.applyDiscount(total, code);
        this.discountedTotal = discounted; 
        return discounted;
    }

    private void notifyObservers(Notification notification) {
        for (OrderObserver observer : new ArrayList<>(observers)) {
            observer.update(notification);
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
    
    private Notification createProductAddedNotification(Product product) {
        String title = "Product Added to Order";
        String body = String.format("Product '%s' (Price: $%.2f) has been added to your order. Total items: %d.",
                product.getName(), product.getPrice(), products.size());
        return new Notification(title, body, this);
    }
    
    private Notification createStatusChangedNotification() {
        String title = "Order Status Updated";
        String body = String.format("Your order status has been updated to '%s'. Total items: %d.", status, products.size());
        return new Notification(title, body, this);
    }

}