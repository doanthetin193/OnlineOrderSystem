package com.onlineordersystem.model.product;

/**
 * Concrete Product class trong Factory Method Pattern.
 * Đại diện cho các sản phẩm điện tử như smartphone, laptop, tablet, etc.
 * 
 * Trong thực tế, class này có thể chứa thêm các thuộc tính đặc thù 
 * của sản phẩm điện tử như warranty, brand, specifications, etc.
 * 
 * @author Online Order System Team
 * @version 1.0
 * @since 2025
 */
public class ElectronicProduct extends Product {
    
    /**
     * Constructor tạo sản phẩm điện tử.
     * 
     * @param name tên sản phẩm điện tử
     * @param price giá sản phẩm điện tử
     */
    public ElectronicProduct(String name, double price) {
        super(name, price);
    }

    /**
     * Trả về mô tả chi tiết của sản phẩm điện tử.
     * 
     * @return chuỗi mô tả sản phẩm điện tử với format cụ thể
     */
    @Override
    public String getDescription() {
        return "Electronic Product: " + name + ", Price: $" + price;
    }
}