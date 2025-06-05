package com.onlineordersystem.core.interfaces;

public interface PaymentFactory {
    PaymentStrategy createPaymentStrategy();
}