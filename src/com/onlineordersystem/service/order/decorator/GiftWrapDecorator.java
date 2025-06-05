package com.onlineordersystem.service.order.decorator;

import com.onlineordersystem.model.inventory.InventoryManager;
import com.onlineordersystem.model.order.Order;
import com.onlineordersystem.service.promotion.DiscountManager;

public class GiftWrapDecorator extends OrderDecorator {
    public GiftWrapDecorator(Order decoratedOrder, InventoryManager inventoryManager, DiscountManager discountManager) {
        super(decoratedOrder, inventoryManager, discountManager);
    }

    @Override
    public double getTotal() {
        return super.getTotal() + 5.0; // Thêm phí gói quà
    }
}