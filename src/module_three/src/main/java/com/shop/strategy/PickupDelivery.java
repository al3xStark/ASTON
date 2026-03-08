package com.shop.strategy;

import com.shop.model.Order;

public class PickupDelivery implements DeliveryStrategy {

    private final String pickupAddress;

    public PickupDelivery(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    @Override
    public void deliver(Order order) {
        System.out.println("  [Самовывоз] Заказ готов к выдаче. " +
            "Пункт выдачи: " + pickupAddress);
    }

    @Override
    public double calculateCost(Order order) {
        return 0;
    }
}
