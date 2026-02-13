package com.proj.stockmanage;

public class drinks extends Product {
    double taxRate = 0.05;

    public drinks(String name, int quantity, String id, double price, String info ) {
        super(name, quantity, id, price, info);
    }

    public double calculateTax() {
        return getPrice()*getQuantity() * taxRate; // Assuming a tax rate of 5% for drinks items
    }

    public double getTaxRate() {
        return taxRate;
    }
}
