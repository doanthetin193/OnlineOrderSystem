package com.onlineordersystem.factory.payment;

import com.onlineordersystem.core.interfaces.PaymentFactory;
import com.onlineordersystem.core.interfaces.PaymentStrategy;
import com.onlineordersystem.service.payment.PayPalPayment;

/**
 * Concrete Factory class trong Abstract Factory Pattern.
 * Chịu trách nhiệm tạo ra PayPalPayment strategy objects.
 * 
 * @author Online Order System Team
 * @version 1.0
 * @since 2025
 */
public class PayPalPaymentFactory implements PaymentFactory {
    
    /**
     * Tạo và trả về một instance của PayPalPayment strategy.
     * 
     * @return PayPalPayment instance để xử lý thanh toán qua PayPal
     */
    @Override
    public PaymentStrategy createPaymentStrategy() {
        return new PayPalPayment();
    }
}