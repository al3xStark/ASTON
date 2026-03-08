package com.shop.model;

public class Customer {

    private final String name;
    private final String email;
    private final String phone;
    private final String address;
    private final double balance;

    public Customer(String name, String email, String phone, String address, double balance) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.balance = balance;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public double getBalance() { return balance; }

}
