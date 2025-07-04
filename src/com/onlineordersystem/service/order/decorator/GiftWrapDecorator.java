package com.onlineordersystem.service.order.decorator;

import com.onlineordersystem.model.inventory.InventoryManager;
import com.onlineordersystem.model.order.Order;
import com.onlineordersystem.service.promotion.DiscountManager;

/**
 * Concrete Decorator class trong Decorator Pattern.
 * Thêm tính năng gói quà (gift wrapping) vào đơn hàng với phí $5.
 * 
 * Decorator Pattern cho phép thêm tính năng mới một cách linh hoạt
 * mà không cần thay đổi structure của Order class gốc.
 * 
 * @author Online Order System Team
 * @version 1.0
 * @since 2025
 */
public class GiftWrapDecorator extends OrderDecorator {
    
    private static final double GIFT_WRAP_FEE = 5.0;
    
    /**
     * Constructor tạo GiftWrapDecorator.
     * 
     * @param decoratedOrder order gốc cần được decorate
     * @param inventoryManager quản lý kho hàng
     * @param discountManager quản lý giảm giá
     */
    public GiftWrapDecorator(Order decoratedOrder, InventoryManager inventoryManager, DiscountManager discountManager) {
        super(decoratedOrder, inventoryManager, discountManager);
    }

    /**
     * Tính tổng tiền bao gồm phí gói quà.
     * 
     * @return tổng tiền của order gốc cộng thêm phí gói quà $5
     */
    @Override
    public double getTotal() {
        return super.getTotal() + GIFT_WRAP_FEE; // Thêm phí gói quà
    }
}