package com.onlineordersystem.model.product;

/**
 * Concrete Product class trong Factory Method Pattern.
 * Đại diện cho các sản phẩm thời trang như quần áo, giày dép, phụ kiện, etc.
 * 
 * Trong thực tế, class này có thể chứa thêm các thuộc tính đặc thù 
 * của sản phẩm thời trang như size, color, material, brand, etc.
 * 
 * @author Online Order System Team
 * @version 1.0
 * @since 2025
 */
public class FashionProduct extends Product {
    
    /**
     * Constructor tạo sản phẩm thời trang.
     * 
     * @param name tên sản phẩm thời trang
     * @param price giá sản phẩm thời trang
     */
    public FashionProduct(String name, double price) {
        super(name, price);
    }

    /**
     * Trả về mô tả chi tiết của sản phẩm thời trang.
     * 
     * @return chuỗi mô tả sản phẩm thời trang với format cụ thể
     */
    @Override
    public String getDescription() {
        return "Fashion Product: " + name + ", Price: $" + price;
    }
}