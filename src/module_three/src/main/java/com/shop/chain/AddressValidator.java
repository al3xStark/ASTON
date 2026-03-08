package com.shop.chain;

import com.shop.model.Order;

public class AddressValidator extends BaseValidator {

    @Override
    public boolean validate(Order order) {
        System.out.println("  [Валидация] Проверка адреса доставки...");
        String address = order.getDeliveryAddress();
        if (address == null || address.isBlank()) {
            System.out.println("  [Валидация] ОШИБКА: адрес доставки не указан.");
            return false;
        }
        System.out.println("  [Валидация] Адрес корректен. ОК");
        return passToNext(order);
    }
}
