package com.onlineordersystem.service.promotion;

import java.util.HashMap;
import java.util.Map;

/**
 * Lớp DiscountManager quản lý các mã giảm giá và chương trình khuyến mãi.
 * Hỗ trợ việc thêm, lưu trữ và áp dụng các discount codes.
 * 
 * Trong thực tế, thông tin discount có thể được lưu trong database
 * và có thể có các loại discount phức tạp hơn (fixed amount, buy-one-get-one, etc.)
 * 
 * @author Online Order System Team
 * @version 1.0
 * @since 2025
 */
public class DiscountManager {
    private Map<String, Double> discounts = new HashMap<>();

    /**
     * Thêm một mã giảm giá mới vào hệ thống.
     * 
     * @param code mã giảm giá (unique identifier)
     * @param percentage phần trăm giảm giá (0-100)
     */
    public void addDiscount(String code, double percentage) {
        discounts.put(code, percentage);
    }

    /**
     * Áp dụng mã giảm giá cho một tổng tiền.
     * 
     * @param total tổng tiền gốc
     * @param code mã giảm giá cần áp dụng
     * @return tổng tiền sau khi áp dụng giảm giá, hoặc total nếu code không hợp lệ
     */
    public double applyDiscount(double total, String code) {
        Double percentage = discounts.get(code);
        if (percentage == null) {
            return total;
        }
        return total * (1 - percentage / 100);
    }
}