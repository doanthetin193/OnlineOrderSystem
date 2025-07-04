package com.onlineordersystem.service.product;

import java.util.List;

import com.onlineordersystem.model.product.Product;

public interface ProductManager {
	void addProduct(Product product);
	void updateProduct(String name, Product updatedProduct);
	void deleteProduct(String name);
	List<Product> listProducts();
}
