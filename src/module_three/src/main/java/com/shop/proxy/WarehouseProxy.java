package com.shop.proxy;

import com.shop.model.Product;

import java.util.HashMap;
import java.util.Map;

public class WarehouseProxy implements Warehouse {

    private final Warehouse realWarehouse;
    private final Map<String, Product> cache = new HashMap<>();

    public WarehouseProxy(Warehouse realWarehouse) {
        this.realWarehouse = realWarehouse;
    }

    @Override
    public Product findProduct(String name) {
        if (cache.containsKey(name)) {
            System.out.println("  [Прокси] Товар \"" + name + "\" найден в кэше - склад не опрашивается.");
            return cache.get(name);
        }
        Product product = realWarehouse.findProduct(name);
        if (product != null) {
            cache.put(name, product);
            System.out.println("  [Прокси] Товар \"" + name + "\" добавлен в кэш.");
        }
        return product;
    }

    @Override
    public boolean isAvailable(String name) {
        System.out.println("  [Прокси] Проверка доступности: \"" + name + "\"");
        if (cache.containsKey(name)) {
            return cache.get(name).getStock() > 0;
        }
        return realWarehouse.isAvailable(name);
    }
}
