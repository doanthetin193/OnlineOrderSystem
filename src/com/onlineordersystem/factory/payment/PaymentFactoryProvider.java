package com.onlineordersystem.factory.payment;

import com.onlineordersystem.core.interfaces.PaymentFactory;

public class PaymentFactoryProvider {
    public static PaymentFactory getPaymentFactory(String paymentType) {
        switch (paymentType.toLowerCase()) {
            case "creditcard":
                return new CreditCardPaymentFactory();
            case "paypal":
                return new PayPalPaymentFactory();
            default:
                throw new IllegalArgumentException("Unknown payment type: " + paymentType);
        }
    }
}