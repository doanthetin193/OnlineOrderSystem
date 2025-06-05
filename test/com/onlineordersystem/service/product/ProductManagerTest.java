package com.onlineordersystem.service.product;

import com.onlineordersystem.factory.product.ProductFactory;
import com.onlineordersystem.model.product.Product;
import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductManagerTest {
    private ProductManager productManager;
    private ProductFactory productFactory;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        productManager = new ProductManager();
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
        productManager.addProduct(product);
        List<Product> products = productManager.listProducts();
        assertEquals(1, products.size());
        assertEquals("Smartphone", products.get(0).getName());
        assertEquals(699.99, products.get(0).getPrice());
        assertTrue(outContent.toString().contains("INSERT INTO products (name, price) VALUES ('Smartphone', 699.99)"));
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    public void testUpdateProduct() {
        Product product = productFactory.createProduct("electronic", "Smartphone", 699.99);
        productManager.addProduct(product);
        Product updatedProduct = productFactory.createProduct("electronic", "Smartphone Pro", 799.99);
        productManager.updateProduct("Smartphone", updatedProduct);
        List<Product> products = productManager.listProducts();
        assertEquals(1, products.size());
        assertEquals("Smartphone Pro", products.get(0).getName());
        assertEquals(799.99, products.get(0).getPrice());
        assertTrue(outContent.toString().contains("UPDATE products SET name = 'Smartphone Pro', price = 799.99 WHERE name = 'Smartphone'"));
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    public void testUpdateProductNotFound() {
        Product updatedProduct = productFactory.createProduct("electronic", "Smartphone Pro", 799.99);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            productManager.updateProduct("NonExistent", updatedProduct);
        });
        assertEquals("Product not found: NonExistent", exception.getMessage());
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    public void testDeleteProduct() {
        Product product = productFactory.createProduct("electronic", "Smartphone", 699.99);
        productManager.addProduct(product);
        productManager.deleteProduct("Smartphone");
        List<Product> products = productManager.listProducts();
        assertTrue(products.isEmpty());
        assertTrue(outContent.toString().contains("DELETE FROM products WHERE name = 'Smartphone'"));
    }

    @Test
    @org.junit.jupiter.api.Order(5)
    public void testListProducts() {
        Product product1 = productFactory.createProduct("electronic", "Smartphone", 699.99);
        Product product2 = productFactory.createProduct("fashion", "T-Shirt", 29.99);
        productManager.addProduct(product1);
        productManager.addProduct(product2);
        List<Product> products = productManager.listProducts();
        assertEquals(2, products.size());
        assertTrue(outContent.toString().contains("SELECT * FROM products"));
    }
}