package com.onlineordersystem.service.order.decorator;

import com.onlineordersystem.model.inventory.InventoryManager;
import com.onlineordersystem.model.order.Order;
import com.onlineordersystem.service.promotion.DiscountManager;

public class SpecialPackagingDecorator extends OrderDecorator {
    public SpecialPackagingDecorator(Order decoratedOrder, InventoryManager inventoryManager, DiscountManager discountManager) {
        super(decoratedOrder, inventoryManager, discountManager);
    }

    @Override
    public double getTotal() {
        return super.getTotal() + 10.0; // Thêm phí đóng gói đặc biệt
    }
}