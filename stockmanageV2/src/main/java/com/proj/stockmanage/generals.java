package com.proj.stockmanage;

public class generals extends Product {
    double taxRate = 0.18;
    
    public generals(String name, int quantity, String id, double price, String info) {
        super(name, quantity, id, price, info);
    }

    public double calculateTax() {
        return getPrice()*getQuantity() * taxRate; // Assuming a tax rate of 18% for general items
    }

    public double getTaxRate() {
        return taxRate;
    }
}
