package com.shop.model;

import java.util.List;

public class Order {

    public enum Status { NEW, VALIDATED, PAID, SHIPPED, DELIVERED }

    private final Customer customer;
    private final List<Product> products;
    private final String deliveryAddress;
    private final String promoCode;
    private final String comment;
    private Status status;

    Order(Customer customer, List<Product> products,
          String deliveryAddress, String promoCode, String comment) {
        this.customer = customer;
        this.products = products;
        this.deliveryAddress = deliveryAddress;
        this.promoCode = promoCode;
        this.comment = comment;
        this.status = Status.NEW;
    }

    public Customer getCustomer() { return customer; }
    public List<Product> getProducts() { return products; }
    public String getDeliveryAddress() { return deliveryAddress; }
    public String getPromoCode() { return promoCode; }
    public String getComment() { return comment; }
    public Status getStatus() { return status; }

    public void setStatus(Status status) { this.status = status; }

    public double getTotalPrice() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

    @Override
    public String toString() {
        return "Заказ {покупатель: " + customer.getName() +
               ", товаров: " + products.size() +
               ", сумма: " + getTotalPrice() + " руб." +
               ", адрес: " + deliveryAddress +
               ", статус: " + status + "}";
    }
}
