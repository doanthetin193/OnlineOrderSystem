package com.onlineordersystem.service.promotion;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class DiscountManagerTest {
    private DiscountManager discountManager;

    @BeforeEach
    public void setUp() {
        discountManager = new DiscountManager();
    }

    @Test
    public void testAddDiscount() {
        discountManager.addDiscount("SALE10", 10.0);
        double total = 100.0;
        double discounted = discountManager.applyDiscount(total, "SALE10");
        assertEquals(90.0, discounted, 0.01);
    }

    @Test
    public void testApplyDiscountInvalidCode() {
        double total = 100.0;
        double discounted = discountManager.applyDiscount(total, "INVALID");
        assertEquals(100.0, discounted, 0.01);
    }

    @Test
    public void testApplyDiscountZeroTotal() {
        discountManager.addDiscount("SALE10", 10.0);
        double total = 0.0;
        double discounted = discountManager.applyDiscount(total, "SALE10");
        assertEquals(0.0, discounted, 0.01);
    }
}