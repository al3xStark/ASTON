package com.shop.proxy;

import com.shop.model.Product;

import java.util.HashMap;
import java.util.Map;

public class RealWarehouse implements Warehouse {

    private final Map<String, Product> storage = new HashMap<>();

    public RealWarehouse() {
        storage.put("Ноутбук", new Product("Ноутбук", 75000, 5));
        storage.put("Мышь", new Product("Мышь", 1500, 20));
        storage.put("Клавиатура", new Product("Клавиатура", 3200, 15));
        storage.put("Монитор", new Product("Монитор", 25000, 3));
    }

    @Override
    public Product findProduct(String name) {
        System.out.println("  [Склад] Запрос к хранилищу: \"" + name + "\"");
        return storage.get(name);
    }

    @Override
    public boolean isAvailable(String name) {
        Product p = storage.get(name);
        return p != null && p.getStock() > 0;
    }
}
