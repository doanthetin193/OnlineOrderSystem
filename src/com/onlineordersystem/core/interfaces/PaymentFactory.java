package com.onlineordersystem.core.interfaces;

/**
 * Interface PaymentFactory triển khai mẫu thiết kế Abstract Factory Pattern.
 * Định nghĩa contract để tạo ra các family của PaymentStrategy objects.
 * 
 * Abstract Factory Pattern cho phép tạo ra nhóm các objects liên quan
 * (các payment strategies) mà không cần chỉ định class cụ thể.
 * 
 * @author Online Order System Team
 * @version 1.0
 * @since 2025
 */
public interface PaymentFactory {
    
    /**
     * Tạo ra một PaymentStrategy instance cụ thể.
     * Mỗi concrete factory sẽ tạo ra strategy tương ứng.
     * 
     * @return PaymentStrategy instance để xử lý thanh toán
     */
    PaymentStrategy createPaymentStrategy();
}