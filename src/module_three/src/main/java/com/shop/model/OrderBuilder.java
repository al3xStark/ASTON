package com.shop.model;

import java.util.ArrayList;
import java.util.List;

public class OrderBuilder {

    private final Customer customer;
    private final String deliveryAddress;
    private final List<Product> products = new ArrayList<>();
    private String promoCode = null;
    private String comment = "";

    public OrderBuilder(Customer customer, String deliveryAddress) {
        if (customer == null)
            throw new IllegalArgumentException("Покупатель обязателен");
        if (deliveryAddress == null || deliveryAddress.isBlank())
            throw new IllegalArgumentException("Адрес доставки обязателен");
        this.customer = customer;
        this.deliveryAddress = deliveryAddress;
    }

    public OrderBuilder addProduct(Product product) {
        if (product == null) throw new IllegalArgumentException("Товар не может быть null");
        products.add(product);
        return this;
    }

    public OrderBuilder promoCode(String promoCode) {
        this.promoCode = promoCode;
        return this;
    }

    public OrderBuilder comment(String comment) {
        this.comment = comment;
        return this;
    }

    public Order build() {
        if (products.isEmpty())
            throw new IllegalStateException("Заказ должен содержать хотя бы один товар");
        return new Order(customer, new ArrayList<>(products), deliveryAddress, promoCode, comment);
    }
}
