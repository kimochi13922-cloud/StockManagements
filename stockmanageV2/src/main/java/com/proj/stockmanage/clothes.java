package com.proj.stockmanage;

public class clothes extends Product {
    double taxRate = 0.12;

    public clothes(String name, int quantity, String id, double price, String description ) {
        super(name, quantity, id, price, description);
    }

    public double calculateTax() {
        return getPrice()*getQuantity() * 0.12; // Assuming a tax rate of 12% for clothes
    }
    
    public double getTaxRate() {
        return taxRate;
    }
}
