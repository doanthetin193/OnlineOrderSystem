package com.onlineordersystem;

import java.util.Arrays;

import com.onlineordersystem.core.database.DatabaseConnection;
import com.onlineordersystem.core.interfaces.PaymentFactory;
import com.onlineordersystem.core.interfaces.PaymentStrategy;
import com.onlineordersystem.factory.payment.PaymentFactoryProvider;
import com.onlineordersystem.factory.product.ProductFactory;
import com.onlineordersystem.model.inventory.InventoryManager;
import com.onlineordersystem.model.inventory.StubInventoryManager;
import com.onlineordersystem.model.order.Order;
import com.onlineordersystem.model.order.OrderObserver;
import com.onlineordersystem.model.product.Product;
import com.onlineordersystem.service.order.decorator.GiftWrapDecorator;
import com.onlineordersystem.service.order.decorator.SpecialPackagingDecorator;
import com.onlineordersystem.service.product.ProductManager;
import com.onlineordersystem.service.product.StubProductManager;
import com.onlineordersystem.service.promotion.DiscountManager;
import com.onlineordersystem.service.report.ReportGenerator;
import com.onlineordersystem.service.report.StubReportGenerator;

public class Main {
    public static void main(String[] args) {
        // Singleton: Kết nối cơ sở dữ liệu
        DatabaseConnection db = DatabaseConnection.getInstance();
        db.executeQuery("SELECT * FROM products");

        // Factory Method: Tạo sản phẩm
        ProductFactory productFactory = new ProductFactory();
        Product phone = productFactory.createProduct("electronic", "Smartphone", 699.99);
        Product shirt = productFactory.createProduct("fashion", "T-Shirt", 29.99);

        // Quản lý sản phẩm
        ProductManager productManager = new StubProductManager();
        productManager.addProduct(phone);
        productManager.addProduct(shirt);
        System.out.println("Product List: ");
        for (Product p : productManager.listProducts()) {
            System.out.println(p.getDescription());
        }
        productManager.updateProduct("Smartphone", productFactory.createProduct("electronic", "Smartphone Pro", 799.99));
        productManager.deleteProduct("T-Shirt");
        System.out.println("Updated Product List: ");
        for (Product p : productManager.listProducts()) {
            System.out.println(p.getDescription());
        }

        // Quản lý kho
        InventoryManager inventoryManager = new StubInventoryManager();
        inventoryManager.addProduct(phone, 10);
        inventoryManager.addProduct(shirt, 20);

        // Khuyến mãi
        DiscountManager discountManager = new DiscountManager();
        discountManager.addDiscount("SALE10", 10.0);

        // Tạo đơn hàng
        Order order = new Order(inventoryManager, discountManager);

        OrderObserver user = new OrderObserver("John Doe");
        order.addObserver(user);
        order.addProduct(phone);
        order.addProduct(shirt);

        // Abstract Factory & Strategy: Chọn phương thức thanh toán
        PaymentFactory paymentFactory = PaymentFactoryProvider.getPaymentFactory("creditcard");
        PaymentStrategy paymentStrategy = paymentFactory.createPaymentStrategy();
        order.setPaymentStrategy(paymentStrategy);

        // Decorator: Thêm tính năng bổ sung
        Order decoratedOrder = new GiftWrapDecorator(
                new SpecialPackagingDecorator(order, inventoryManager, discountManager),
                inventoryManager, discountManager
        );
        double total = decoratedOrder.getTotal();
        System.out.println("Total cost with decorations: $" + total);

        // Áp dụng khuyến mãi
        double discountedTotal = order.applyDiscount("SALE10");
        System.out.println("Total cost after discount: $" + discountedTotal);

        // Xử lý thanh toán
        order.processPayment(discountedTotal);

        // Báo cáo
        ReportGenerator reportGenerator = new StubReportGenerator();
        double revenue = reportGenerator.calculateRevenue(Arrays.asList(order));
        String bestSeller = reportGenerator.findBestSellingProduct(Arrays.asList(order));
        System.out.println("Total Revenue: $" + revenue);
        System.out.println("Best Selling Product: " + bestSeller);
    }
}