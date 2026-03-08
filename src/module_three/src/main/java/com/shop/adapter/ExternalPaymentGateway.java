package com.shop.adapter;

public class ExternalPaymentGateway {

    public String initiateTransaction(String clientEmail, double amount) {
        System.out.println("  [Внешняя платёжная система] Транзакция инициирована: " +
            clientEmail + ", сумма: " + amount + " руб.");
        return "TXN-" + System.currentTimeMillis();
    }

    public boolean confirmTransaction(String transactionId) {
        System.out.println("  [Внешняя платёжная система] Транзакция " + transactionId + " подтверждена.");
        return true;
    }
}
