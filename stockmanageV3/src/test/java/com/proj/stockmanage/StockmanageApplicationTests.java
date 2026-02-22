package com.proj.stockmanage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    private productService service;

    @BeforeEach
    void setUp() {
        service = new productService();
        service.init(); // เรียกใช้ init เพื่อให้มีข้อมูลเริ่มต้น (Laptop, Painkiller, Chocolate)
    }

    @Test
    @DisplayName("ทดสอบการดึงสินค้าทั้งหมด (Initial Data)")
    void testGetAllItems() {
        List<Product> items = service.getAllItems();
        assertEquals(3, items.size(), "ข้อมูลเริ่มต้นควรมี 3 ชิ้น");
    }

    @Test
    @DisplayName("ทดสอบการเพิ่มสินค้าใหม่ (ID ใหม่)")
    void testAddItem_NewProduct() {
        Product newBox = new generals("Box", 5, "STK004", 10.0, "Paper box");
        service.addItem(newBox);
        
        assertEquals(4, service.getTotalProduct());
        assertTrue(service.getAllItems().contains(newBox));
    }

    @Test
    @DisplayName("ทดสอบการเพิ่มสินค้าที่ ID ซ้ำ (ต้องเพิ่มจำนวน Stock เข้าไปในตัวเดิม)")
    void testAddItem_ExistingProduct() {
        // เดิม Laptop (STK001) มี 10 ชิ้น
        Product extraLaptop = new generals("Laptop", 5, "STK001", 800.0, "Same ID");
        service.addItem(extraLaptop);

        assertEquals(3, service.getTotalProduct(), "จำนวนชนิดสินค้าต้องเท่าเดิม");
        
    }

    @Test
    @DisplayName("ทดสอบการลบสินค้า")
    void testDeleteItem() {
        service.deleteItem("STK001"); // ลบ Laptop
        assertEquals(2, service.getTotalProduct());
    }

    @Test
    @DisplayName("ทดสอบการคำนวณจำนวนสินค้าที่ Stock ต่ำ (Low Stock < 10)")
    void testGetTotalLowStock() {
        // ข้อมูลเริ่มต้น: Laptop(10), Painkiller(0), Chocolate(5)
        // ตัวที่น้อยกว่า 10 คือ Painkiller และ Chocolate
        assertEquals(2, service.getTotalLowStock());
    }

    @Test
    @DisplayName("ทดสอบการสร้าง ID ถัดไป")
    void testGenerateNextId() {
        String nextId = service.generateNextId();
        assertEquals("STK004", nextId, "ID ถัดไปต้องเป็น STK004");
    }
}