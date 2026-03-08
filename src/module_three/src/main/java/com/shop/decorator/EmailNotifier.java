package com.shop.decorator;

import com.shop.model.Order;

public class EmailNotifier implements Notifier {

    @Override
    public void notify(Order order) {
        System.out.println("  [Email] Отправлено письмо на " +
            order.getCustomer().getEmail() + ": заказ оформлен. " + order);
    }
}
