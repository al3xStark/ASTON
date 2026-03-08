package com.shop.adapter;

import com.shop.model.Order;

public class PaymentAdapter implements PaymentService {

    private final ExternalPaymentGateway gateway;

    public PaymentAdapter(ExternalPaymentGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public boolean pay(Order order) {
        String transactionId = gateway.initiateTransaction(
            order.getCustomer().getEmail(),
            order.getTotalPrice()
        );
        return gateway.confirmTransaction(transactionId);
    }
}
