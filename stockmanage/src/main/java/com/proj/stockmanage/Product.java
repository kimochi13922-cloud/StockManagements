package com.proj.stockmanage;

abstract class Product {
    private String name;
    private int quantity;
    private String id;
    private double price;
    private String info;
    


    //constructor for setting values
    public Product(String name, int quantity, String id, double price,  String info) {
        this.name = name;
        this.quantity = quantity;
        this.id = id;
        this.price = price;
        this.info = info;

    }

    //getters
    public String getName() {
        return this.name;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public String getId() {
        return this.id;
    }

    public double getPrice() {
        return this.price;
    }

    public String getInfo() {
        return this.info;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }   
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setInfo(String info) {
        this.info = info;
    }

    public void addStock(int amount) {
        this.quantity += amount;
    }

    public String getStatus() {
    if (this.quantity <= 0) {
        return "Out of stock"; // เช็คศูนย์ก่อน
    } else if (this.quantity < 10) {
        return "Low stock";    // เช็คน้อยกว่าสิบ (แต่มากกว่าศูนย์แน่ๆ เพราะผ่าน check แรกมาแล้ว)
    } else {
        return "In stock";     // มากกว่าหรือเท่ากับสิบ
    }
}

    

    //method to calculate total value of product based on quantity and price
    public double calculateTotalValue() {
        return this.quantity * this.price;
    }

    //abstract method to calculate tax, to be implemented by subclasses
    public abstract double calculateTax();

    public String getCategory() {
        String category = this.getClass().getSimpleName();
        return category;
    }

    abstract public double getTaxRate();

}
