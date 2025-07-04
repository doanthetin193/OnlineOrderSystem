package com.onlineordersystem.service.payment;

import com.onlineordersystem.core.interfaces.PaymentStrategy;

/**
 * Concrete Strategy class trong Strategy Pattern.
 * Triển khai logic thanh toán qua PayPal.
 * 
 * Trong thực tế, class này sẽ chứa logic kết nối với PayPal API,
 * OAuth authentication, transaction processing, etc.
 * 
 * @author Online Order System Team
 * @version 1.0
 * @since 2025
 */
public class PayPalPayment implements PaymentStrategy {
    
    /**
     * Thực hiện thanh toán qua PayPal.
     * Mô phỏng quá trình xử lý payment với PayPal service.
     * 
     * @param amount số tiền cần thanh toán
     */
    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " via PayPal.");
    }
}