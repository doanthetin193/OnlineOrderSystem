package com.onlineordersystem.model.inventory;

import com.onlineordersystem.factory.product.ProductFactory;
import com.onlineordersystem.model.product.Product;
import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InventoryManagerTest {
    private StubInventoryManager inventoryManager;
    private ProductFactory productFactory;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        inventoryManager = new StubInventoryManager();
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
        Product product = productFactory.createProduct("electronic", "Smartphone", 699.99);
        inventoryManager.addProduct(product, 10);
        assertTrue(inventoryManager.checkAvailability(product, 5));
        assertTrue(outContent.toString().contains("INSERT INTO inventory (name, quantity) VALUES ('Smartphone', 10)"));
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    public void testCheckAvailability() {
        Product product = productFactory.createProduct("electronic", "Smartphone", 699.99);
        inventoryManager.addProduct(product, 10);
        assertTrue(inventoryManager.checkAvailability(product, 10));
        assertFalse(inventoryManager.checkAvailability(product, 11));
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    public void testUpdateInventory() {
        Product product = productFactory.createProduct("electronic", "Smartphone", 699.99);
        inventoryManager.addProduct(product, 10);
        inventoryManager.updateInventory(product, 3);
        assertTrue(inventoryManager.checkAvailability(product, 7));
        assertFalse(inventoryManager.checkAvailability(product, 8));
        assertTrue(outContent.toString().contains("UPDATE inventory SET quantity = 7 WHERE name = 'Smartphone'"));
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    public void testUpdateInventoryNotEnoughStock() {
        Product product = productFactory.createProduct("electronic", "Smartphone", 699.99);
        inventoryManager.addProduct(product, 5);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            inventoryManager.updateInventory(product, 6);
        });
        assertEquals("Not enough stock for Smartphone", exception.getMessage());
    }
}