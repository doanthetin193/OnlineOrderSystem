package com.onlineordersystem.model.inventory;

import com.onlineordersystem.model.product.Product;

public interface InventoryManager {

    /**
     * Thêm một sản phẩm vào kho với số lượng nhất định.
     *
     * @param product  sản phẩm cần thêm
     * @param quantity số lượng sản phẩm
     */
    void addProduct(Product product, int quantity);

    /**
     * Kiểm tra xem kho có đủ số lượng sản phẩm yêu cầu hay không.
     *
     * @param product  sản phẩm cần kiểm tra
     * @param quantity số lượng mong muốn
     * @return true nếu đủ hàng, ngược lại false
     */
    boolean checkAvailability(Product product, int quantity);

    /**
     * Cập nhật lại kho sau khi bán/đặt hàng (giảm số lượng tồn kho).
     *
     * @param product  sản phẩm cần cập nhật
     * @param quantity số lượng đã bán/đặt
     * @throws IllegalArgumentException nếu kho không đủ hàng
     */
    void updateInventory(Product product, int quantity);
}

