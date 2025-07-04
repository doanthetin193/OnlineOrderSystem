package com.onlineordersystem.service.report;

import java.util.List;

import com.onlineordersystem.model.order.Order;

public interface ReportGenerator {
	double calculateRevenue(List<Order> orders);
	String findBestSellingProduct(List<Order> orders);
}
