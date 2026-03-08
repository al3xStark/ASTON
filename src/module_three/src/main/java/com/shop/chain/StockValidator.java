package com.shop.chain;

import com.shop.model.Order;
import com.shop.model.Product;

public class StockValidator extends BaseValidator {

    @Override
    public boolean validate(Order order) {
        System.out.println("  [Валидация] Проверка наличия товаров...");
        for (Product product : order.getProducts()) {
            if (product.getStock() <= 0) {
                System.out.println("  [Валидация] ОШИБКА: товар \"" + product.getName() + "\" отсутствует на складе.");
                return false;
            }
        }
        System.out.println("  [Валидация] Все товары в наличии. ОК");
        return passToNext(order);
    }
}
