package com.proj.stockmanage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class productService {

    public List<Product> allitems = new ArrayList<>();

    @PostConstruct
    public void init() {
        allitems.add(new generals("Laptop", 10, "STK001", 800.00, "High performance laptop"));
        allitems.add(new medicines("Painkiller", 0, "STK002", 5.00, "Effective pain relief"));
        allitems.add(new foods("Chocolate", 5, "STK003", 2.50, "Delicious dark chocolate"));
    }


    // Getters
    public List<Product> getAllItems() {
        return allitems;
    }

    // ID Generation
    private int lastID = 3;
    public String generateNextId() {
        return "STK" + String.format("%03d", lastID + 1);
    }


    // CRUD Operations
    public void addItem(Product newProduct) {
    if (newProduct.getId() == null) return; // เช็ค Null พื้นฐาน

    // 1. ค้นหาสินค้าที่มี ID ตรงกับสินค้าใหม่
    Optional<Product> existingProduct = allitems.stream()
            .filter(p -> p.getId().equals(newProduct.getId()))
            .findFirst();

    if (existingProduct.isPresent()) {
        // 2. ถ้าเจอ (มี ID อยู่แล้ว): ดึงตัวเก่าออกมาแล้วบวก Quantity เพิ่มเข้าไป
        Product oldProduct =    existingProduct.get();
        oldProduct.addStock(newProduct.getQuantity());
        
        
        
    } else {
        // 3. ถ้าไม่เจอ: เพิ่มเป็นสินค้าชิ้นใหม่เข้าไปใน List
        allitems.add(newProduct);
        lastID++; // เพิ่ม ID สำหรับสินค้าชิ้นถัดไป;
    }
}

    public void editItem(Product updatedProduct) {
        for (int i = 0; i < allitems.size(); i++) {
            if (allitems.get(i).getId().equals(updatedProduct.getId())) {
                allitems.set(i, updatedProduct);
                return;
            }
        }
    }

    public void deleteItem(String id) {
        allitems.removeIf(product -> product.getId().equals(id));
    }


    // Utility Methods
   

    public double calculateStockValue(List<Product> products) {
        return products.stream()
                .mapToDouble(Product::calculateTotalValue)
                .sum();
    }

     public int getTotalProduct(){
        return allitems.size();
    }

    public int getTotalLowStock() {
        // สมมติว่าถ้าน้อยกว่า 5 ชิ้น คือ Low Stock
        return (int) allitems.stream()
                .filter(product -> product.getQuantity() < 10) 
                .count();
    }


  public double calculateTotalTax(List<Product> products) {
        return products.stream()
                .mapToDouble(Product::calculateTax)  // เติมชื่อเมธอด getTax ตรงนี้
                .sum();
    }
}
