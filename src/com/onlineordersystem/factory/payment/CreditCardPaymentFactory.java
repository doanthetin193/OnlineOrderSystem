package com.onlineordersystem.factory.payment;

import com.onlineordersystem.core.interfaces.PaymentFactory;
import com.onlineordersystem.core.interfaces.PaymentStrategy;
import com.onlineordersystem.service.payment.CreditCardPayment;

/**
 * Concrete Factory class trong Abstract Factory Pattern.
 * Chịu trách nhiệm tạo ra CreditCardPayment strategy objects.
 * 
 * @author Online Order System Team
 * @version 1.0
 * @since 2025
 */
public class CreditCardPaymentFactory implements PaymentFactory {
    
    /**
     * Tạo và trả về một instance của CreditCardPayment strategy.
     * 
     * @return CreditCardPayment instance để xử lý thanh toán qua thẻ tín dụng
     */
    @Override
    public PaymentStrategy createPaymentStrategy() {
        return new CreditCardPayment();
    }
}