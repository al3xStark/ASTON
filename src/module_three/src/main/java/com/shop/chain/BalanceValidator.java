package com.shop.chain;

import com.shop.model.Order;

public class BalanceValidator extends BaseValidator {

    @Override
    public boolean validate(Order order) {
        System.out.println("  [Валидация] Проверка баланса покупателя...");
        double total = order.getTotalPrice();
        double balance = order.getCustomer().getBalance();
        if (balance < total) {
            System.out.println("  [Валидация] ОШИБКА: недостаточно средств. " +
                "Нужно: " + total + " руб., есть: " + balance + " руб.");
            return false;
        }
        System.out.println("  [Валидация] Баланс достаточен. ОК");
        return passToNext(order);
    }
}
