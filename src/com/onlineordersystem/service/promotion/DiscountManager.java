package com.onlineordersystem.service.promotion;

import java.util.HashMap;
import java.util.Map;

public class DiscountManager {
    private Map<String, Double> discounts = new HashMap<>();

    public void addDiscount(String code, double percentage) {
        discounts.put(code, percentage);
    }

    public double applyDiscount(double total, String code) {
        Double percentage = discounts.get(code);
        if (percentage == null) {
            return total;
        }
        return total * (1 - percentage / 100);
    }
}