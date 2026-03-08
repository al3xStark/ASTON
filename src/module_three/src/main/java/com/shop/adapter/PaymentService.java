package com.shop.adapter;

import com.shop.model.Order;

public interface PaymentService {
    boolean pay(Order order);
}
