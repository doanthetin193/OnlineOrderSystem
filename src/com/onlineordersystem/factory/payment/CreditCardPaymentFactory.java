package com.onlineordersystem.factory.payment;

import com.onlineordersystem.core.interfaces.PaymentFactory;
import com.onlineordersystem.core.interfaces.PaymentStrategy;
import com.onlineordersystem.service.payment.CreditCardPayment;

public class CreditCardPaymentFactory implements PaymentFactory {
    @Override
    public PaymentStrategy createPaymentStrategy() {
        return new CreditCardPayment();
    }
}