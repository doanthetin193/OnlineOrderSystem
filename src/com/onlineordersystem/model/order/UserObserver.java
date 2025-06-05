package com.onlineordersystem.model.order;

import com.onlineordersystem.core.interfaces.OrderObserver;

public class UserObserver implements OrderObserver {
    private String userName;

    public UserObserver(String userName) {
        this.userName = userName;
    }

    @Override
    public void update(String status) {
        System.out.println("User " + userName + " received update: " + status);
    }
}