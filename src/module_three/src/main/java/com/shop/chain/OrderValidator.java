package com.shop.chain;

import com.shop.model.Order;

public interface OrderValidator {
    void setNext(OrderValidator next);
    boolean validate(Order order);
}
