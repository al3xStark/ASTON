package com.shop.decorator;

import com.shop.model.Order;

abstract class BaseNotifierDecorator implements Notifier {

    protected final Notifier wrapped;

    protected BaseNotifierDecorator(Notifier wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public void notify(Order order) {
        wrapped.notify(order);
    }
}

class SmsDecorator extends BaseNotifierDecorator {

    public SmsDecorator(Notifier wrapped) {
        super(wrapped);
    }

    @Override
    public void notify(Order order) {
        super.notify(order);
        System.out.println("  [SMS] Отправлено SMS на " +
            order.getCustomer().getPhone() + ": ваш заказ на сумму " +
            order.getTotalPrice() + " руб. оформлен.");
    }
}

class PushDecorator extends BaseNotifierDecorator {

    public PushDecorator(Notifier wrapped) {
        super(wrapped);
    }

    @Override
    public void notify(Order order) {
        super.notify(order);
        System.out.println("  [Push] Push-уведомление: заказ #" +
            order.hashCode() + " передан в обработку.");
    }
}
