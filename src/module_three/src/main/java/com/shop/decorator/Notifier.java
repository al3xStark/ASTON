package com.shop.decorator;

import com.shop.model.Order;

public interface Notifier {
    void notify(Order order);
}
