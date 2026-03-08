package com.shop.strategy;

import com.shop.model.Order;

public interface DeliveryStrategy {
    void deliver(Order order);
    double calculateCost(Order order);
}
