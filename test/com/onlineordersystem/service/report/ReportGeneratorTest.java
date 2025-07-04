package com.onlineordersystem.service.report;

import com.onlineordersystem.factory.product.ProductFactory;
import com.onlineordersystem.model.inventory.StubInventoryManager;
import com.onlineordersystem.model.order.Order;
import com.onlineordersystem.model.product.Product;
import com.onlineordersystem.service.promotion.DiscountManager;
import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ReportGeneratorTest {
    private StubReportGenerator reportGenerator;
    private ProductFactory productFactory;
    private StubInventoryManager inventoryManager;
    private DiscountManager discountManager;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        reportGenerator = new StubReportGenerator();
        productFactory = new ProductFactory();
        inventoryManager = new StubInventoryManager();
        discountManager = new DiscountManager();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        outContent.reset();
    }

    @Test
    public void testCalculateRevenue() {
        Order order = new Order(inventoryManager, discountManager);
        Product product = productFactory.createProduct("electronic", "Smartphone", 100.0);
        inventoryManager.addProduct(product, 10);
        order.addProduct(product);
        discountManager.addDiscount("SALE10", 10.0);
        order.applyDiscount("SALE10"); // $90.0
        double revenue = reportGenerator.calculateRevenue(Arrays.asList(order));
        assertEquals(90.0, revenue, 0.01);
        assertTrue(outContent.toString().contains("SELECT SUM(total) FROM orders"));
    }

    @Test
    public void testFindBestSellingProduct() {
        Order order = new Order(inventoryManager, discountManager);
        Product product1 = productFactory.createProduct("electronic", "Smartphone", 100.0);
        Product product2 = productFactory.createProduct("fashion", "T-Shirt", 29.99);
        inventoryManager.addProduct(product1, 10);
        inventoryManager.addProduct(product2, 10);
        order.addProduct(product1);
        order.addProduct(product1);
        order.addProduct(product2);
        String bestSeller = reportGenerator.findBestSellingProduct(Arrays.asList(order));
        assertEquals("Smartphone", bestSeller);
        assertTrue(outContent.toString().contains("Product counts: {T-Shirt=1, Smartphone=2}"));
    }

    @Test
    public void testFindBestSellingProductNoOrders() {
        String bestSeller = reportGenerator.findBestSellingProduct(Arrays.asList());
        assertEquals("No products", bestSeller);
    }
}