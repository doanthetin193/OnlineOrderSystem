package com.onlineordersystem.model.product;

/**
 * Abstract class Product định nghĩa base structure cho tất cả các loại sản phẩm
 * trong hệ thống. Đây là parent class trong Factory Method Pattern.
 * 
 * Sử dụng Template Method pattern để định nghĩa các thuộc tính chung 
 * và abstract method cho việc hiển thị mô tả sản phẩm.
 * 
 * @author Online Order System Team
 * @version 1.0
 * @since 2025
 */
public abstract class Product {
    protected String name;
    protected double price;

    /**
     * Constructor để khởi tạo sản phẩm với tên và giá.
     * 
     * @param name tên sản phẩm (không được null hoặc empty)
     * @param price giá sản phẩm (phải >= 0)
     */
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    /**
     * Getter cho tên sản phẩm.
     * 
     * @return tên sản phẩm
     */
    public String getName() {
        return name;
    }

    /**
     * Getter cho giá sản phẩm.
     * 
     * @return giá sản phẩm
     */
    public double getPrice() {
        return price;
    }

    /**
     * Abstract method để lấy mô tả chi tiết của sản phẩm.
     * Mỗi concrete product class sẽ implement method này theo cách riêng.
     * 
     * @return mô tả chi tiết của sản phẩm
     */
    public abstract String getDescription();
}