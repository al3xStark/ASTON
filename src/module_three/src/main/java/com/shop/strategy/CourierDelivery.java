package com.shop.strategy;

import com.shop.model.Order;

public class CourierDelivery implements DeliveryStrategy {

    @Override
    public void deliver(Order order) {
        System.out.println("  [Курьер] Заказ передан курьерской службе. " +
            "Адрес доставки: " + order.getDeliveryAddress());
    }

    @Override
    public double calculateCost(Order order) {
        return order.getTotalPrice() >= 3000 ? 0 : 299;
    }
}
