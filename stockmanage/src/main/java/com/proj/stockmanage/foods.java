package com.proj.stockmanage;

public class foods extends Product {
    double taxRate = 0.05;

    public foods(String name, int quantity, String id, double price, String info) {
        super(name, quantity, id, price, info);
    }

    public double calculateTax() {
        return getPrice()*getQuantity() * 0.05; // Assuming a tax rate of 5% for food items
    }

    public double getTaxRate() {
        return taxRate;
    }
}
