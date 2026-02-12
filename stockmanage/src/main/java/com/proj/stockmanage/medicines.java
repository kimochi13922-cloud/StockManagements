package com.proj.stockmanage;

public class medicines extends Product {
    double taxRate = 0.03;

    public medicines(String name, int quantity, String id, double price, String info) {
        super(name, quantity, id, price, info);
    }
    public double calculateTax() {
        return getPrice()*getQuantity() * taxRate; // Assuming a tax rate of 3% for medicine items
    }

    public double getTaxRate() {
        return taxRate;
    }
}
