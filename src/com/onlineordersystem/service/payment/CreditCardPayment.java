package com.onlineordersystem.service.payment;

import com.onlineordersystem.core.interfaces.PaymentStrategy;

/**
 * Concrete Strategy class trong Strategy Pattern.
 * Triển khai logic thanh toán qua thẻ tín dụng (Credit Card).
 * 
 * Trong thực tế, class này sẽ chứa logic kết nối với payment gateway,
 * validation thông tin thẻ, xử lý transaction, etc.
 * 
 * @author Online Order System Team
 * @version 1.0
 * @since 2025
 */
public class CreditCardPayment implements PaymentStrategy {
    
    /**
     * Thực hiện thanh toán qua thẻ tín dụng.
     * Mô phỏng quá trình xử lý payment với credit card.
     * 
     * @param amount số tiền cần thanh toán
     */
    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " via Credit Card.");
    }
}