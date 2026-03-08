package com.shop.strategy;

import com.shop.model.Order;

public class PostDelivery implements DeliveryStrategy {

    @Override
    public void deliver(Order order) {
        System.out.println("  [Почта] Заказ отправлен Почтой России. " +
            "Адрес: " + order.getDeliveryAddress() +
            ". Срок доставки: 7-14 дней.");
    }

    @Override
    public double calculateCost(Order order) {
        return order.getTotalPrice() * 0.05;
    }
}
