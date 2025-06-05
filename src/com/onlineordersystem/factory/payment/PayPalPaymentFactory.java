package com.onlineordersystem.factory.payment;

import com.onlineordersystem.core.interfaces.PaymentFactory;
import com.onlineordersystem.core.interfaces.PaymentStrategy;
import com.onlineordersystem.service.payment.PayPalPayment;

public class PayPalPaymentFactory implements PaymentFactory {
    @Override
    public PaymentStrategy createPaymentStrategy() {
        return new PayPalPayment();
    }
}