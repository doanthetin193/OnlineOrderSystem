package com.onlineordersystem.service.payment;

import com.onlineordersystem.core.interfaces.PaymentStrategy;

public class PayPalPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " via PayPal.");
    }
}