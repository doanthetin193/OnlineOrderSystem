package com.onlineordersystem.service.order.decorator;

import com.onlineordersystem.model.inventory.InventoryManager;
import com.onlineordersystem.model.order.Order;
import com.onlineordersystem.service.promotion.DiscountManager;

/**
 * Concrete Decorator class trong Decorator Pattern.
 * Thêm tính năng đóng gói đặc biệt (special packaging) vào đơn hàng với phí $10.
 * 
 * Có thể được sử dụng kết hợp với các decorators khác như GiftWrapDecorator
 * để tạo ra các combination services phong phú.
 * 
 * @author Online Order System Team
 * @version 1.0
 * @since 2025
 */
public class SpecialPackagingDecorator extends OrderDecorator {
    
    private static final double SPECIAL_PACKAGING_FEE = 10.0;
    
    /**
     * Constructor tạo SpecialPackagingDecorator.
     * 
     * @param decoratedOrder order gốc cần được decorate
     * @param inventoryManager quản lý kho hàng
     * @param discountManager quản lý giảm giá
     */
    public SpecialPackagingDecorator(Order decoratedOrder, InventoryManager inventoryManager, DiscountManager discountManager) {
        super(decoratedOrder, inventoryManager, discountManager);
    }

    /**
     * Tính tổng tiền bao gồm phí đóng gói đặc biệt.
     * 
     * @return tổng tiền của order gốc cộng thêm phí đóng gói đặc biệt $10
     */
    @Override
    public double getTotal() {
        return super.getTotal() + SPECIAL_PACKAGING_FEE; // Thêm phí đóng gói đặc biệt
    }
}