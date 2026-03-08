package com.shop.proxy;

import com.shop.model.Product;

public interface Warehouse {
    Product findProduct(String name);
    boolean isAvailable(String name);
}
