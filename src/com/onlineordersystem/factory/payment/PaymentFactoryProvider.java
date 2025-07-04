package com.onlineordersystem.factory.payment;

import com.onlineordersystem.core.interfaces.PaymentFactory;

/**
 * Lớp PaymentFactoryProvider là một Factory Provider trong Abstract Factory Pattern.
 * Chịu trách nhiệm chọn và trả về PaymentFactory phù hợp dựa trên loại thanh toán.
 * 
 * Lớp này tách biệt logic chọn factory khỏi client code, tăng tính linh hoạt
 * và dễ dàng mở rộng khi thêm các phương thức thanh toán mới.
 * 
 * @author Online Order System Team
 * @version 1.0
 * @since 2025
 */
public class PaymentFactoryProvider {
    
    /**
     * Trả về PaymentFactory phù hợp dựa trên loại thanh toán được yêu cầu.
     * 
     * @param paymentType loại thanh toán ("creditcard", "paypal", etc.)
     * @return PaymentFactory instance tương ứng
     * @throws IllegalArgumentException nếu payment type không được hỗ trợ
     */
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