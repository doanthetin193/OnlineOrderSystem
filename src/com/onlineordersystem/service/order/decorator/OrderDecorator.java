package com.onlineordersystem.service.order.decorator;

import com.onlineordersystem.model.inventory.InventoryManager;
import com.onlineordersystem.model.order.Order;
import com.onlineordersystem.service.promotion.DiscountManager;

public abstract class OrderDecorator extends Order {
    protected Order decoratedOrder;

    public OrderDecorator(Order decoratedOrder, InventoryManager inventoryManager, DiscountManager discountManager) {
        super(inventoryManager, discountManager); // Gọi constructor của Order với các tham số
        this.decoratedOrder = decoratedOrder;
    }

    @Override
    public double getTotal() {
        return decoratedOrder.getTotal();
    }
}