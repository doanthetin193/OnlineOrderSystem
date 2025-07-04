package com.onlineordersystem.core.interfaces;

/**
 * Interface PaymentStrategy triển khai mẫu thiết kế Strategy Pattern.
 * Định nghĩa contract cho các chiến lược thanh toán khác nhau như Credit Card, PayPal, etc.
 * 
 * Strategy Pattern cho phép thay đổi thuật toán thanh toán một cách linh hoạt
 * mà không cần thay đổi code của client (Order class).
 * 
 * @author Online Order System Team
 * @version 1.0
 * @since 2025
 */
public interface PaymentStrategy {
    
    /**
     * Thực hiện thanh toán với số tiền được chỉ định.
     * Mỗi implementation cụ thể sẽ có cách xử lý thanh toán riêng.
     * 
     * @param amount số tiền cần thanh toán (phải > 0)
     */
    void pay(double amount);
}