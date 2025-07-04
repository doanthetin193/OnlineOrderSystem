package com.onlineordersystem.model.order;

import com.onlineordersystem.factory.product.ProductFactory;
import com.onlineordersystem.model.inventory.StubInventoryManager;
import com.onlineordersystem.model.product.Product;
import com.onlineordersystem.service.order.decorator.GiftWrapDecorator;
import com.onlineordersystem.service.order.decorator.SpecialPackagingDecorator;
import com.onlineordersystem.service.payment.CreditCardPayment;
import com.onlineordersystem.service.promotion.DiscountManager;
import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderTest {
    private Order order;
    private StubInventoryManager inventoryManager;
    private DiscountManager discountManager;
    private ProductFactory productFactory;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        inventoryManager = new StubInventoryManager();
        discountManager = new DiscountManager();
        order = new Order(inventoryManager, discountManager);
        productFactory = new ProductFactory();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        outContent.reset();
    }

    @Test
    @org.junit.jupiter.api.Order(1)
    public void testAddProduct() {
        Product product = productFactory.createProduct("electronic", "Smartphone", 100.0);
        inventoryManager.addProduct(product, 10);
        OrderObserver observer = new OrderObserver("John Doe");
        order.addObserver(observer);
        order.addProduct(product);
        assertEquals(1, order.getProducts().size());
        assertEquals("Smartphone", order.getProducts().get(0).getName());
        assertTrue(outContent.toString().contains("User John Doe received notification: Product Added to Order - Product 'Smartphone' (Price: $100.00) has been added to your order. Total items: 1."));
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    public void testAddProductNotEnoughStock() {
        Product product = productFactory.createProduct("electronic", "Smartphone", 100.0);
        inventoryManager.addProduct(product, 1);
        order.addProduct(product);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            order.addProduct(product);
        });
        assertEquals("Not enough stock for Smartphone", exception.getMessage());
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    public void testApplyDiscount() {
        Product product = productFactory.createProduct("electronic", "Smartphone", 100.0);
        inventoryManager.addProduct(product, 10);
        order.addProduct(product);
        discountManager.addDiscount("SALE10", 10.0);
        double discounted = order.applyDiscount("SALE10");
        assertEquals(90.0, discounted, 0.01);
        assertEquals(90.0, order.getDiscountedTotal(), 0.01);
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    public void testProcessPayment() {
        Product product = productFactory.createProduct("electronic", "Smartphone", 100.0);
        inventoryManager.addProduct(product, 10);
        order.addProduct(product);
        order.setPaymentStrategy(new CreditCardPayment());
        order.processPayment();
        assertEquals("Paid", order.getStatus());
        assertEquals(100.0, order.getDiscountedTotal(), 0.01);
        assertTrue(outContent.toString().contains("Paid $100.0 via Credit Card"));
    }

    @Test
    @org.junit.jupiter.api.Order(5)
    public void testProcessPaymentNoStrategy() {
        Product product = productFactory.createProduct("electronic", "Smartphone", 100.0);
        inventoryManager.addProduct(product, 10);
        order.addProduct(product);
        order.processPayment();
        assertTrue(outContent.toString().contains("No payment strategy selected."));
    }

    @Test
    @org.junit.jupiter.api.Order(6)
    public void testDecorators() {
        Product product = productFactory.createProduct("electronic", "Smartphone", 100.0);
        inventoryManager.addProduct(product, 10);
        order.addProduct(product);
        Order decoratedOrder = new GiftWrapDecorator(
                new SpecialPackagingDecorator(order, inventoryManager, discountManager),
                inventoryManager, discountManager
        );
        assertEquals(115.0, decoratedOrder.getTotal(), 0.01); // 100 + 5 + 10
    }

    @Test
    @org.junit.jupiter.api.Order(7)
    public void testObserverNotification() {
        Product product = productFactory.createProduct("electronic", "Smartphone", 100.0);
        inventoryManager.addProduct(product, 10);
        OrderObserver observer = new OrderObserver("John");
        order.addObserver(observer);
        order.addProduct(product);
        assertTrue(outContent.toString().contains("User John received notification"));
    }
}