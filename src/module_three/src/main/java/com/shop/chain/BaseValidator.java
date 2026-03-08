package com.shop.chain;

import com.shop.model.Order;

public abstract class BaseValidator implements OrderValidator {

    private OrderValidator next;

    @Override
    public void setNext(OrderValidator next) {
        this.next = next;
    }

    protected boolean passToNext(Order order) {
        if (next == null) return true;
        return next.validate(order);
    }
}
