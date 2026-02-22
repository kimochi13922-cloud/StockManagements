package com.proj.stockmanage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class stockController {

    @Autowired
    private productService productService;

    @GetMapping("/stock")
    public String stockPage(Model model) {
        List<Product> products = productService.getAllItems();
        model.addAttribute("products", products);
        model.addAttribute("genId", productService.generateNextId());
        model.addAttribute("TotalProduct", productService.getTotalProduct());
        model.addAttribute("TotalValue", productService.calculateStockValue(products));
        model.addAttribute("TotalLowproducts", productService.getTotalLowStock());
        model.addAttribute("TotalTax", productService.calculateTotalTax(products));
        return "stock";
    }

    @PostMapping("/add-product")
    public String addProduct(@RequestParam("name") String name,
        @RequestParam("id") String id,
        @RequestParam("category") String category,
        @RequestParam("info") String info,
        @RequestParam("quantity") int quantity,
        @RequestParam("price") double price,
        RedirectAttributes redirectAttrs) {
            switch (category) {
                case "foods":
                    Product newFood = new foods(name, quantity, id, price, info);
                    productService.addItem(newFood);
                    break;
                case "drinks":
                    Product newDrink = new drinks(name, quantity, id, price, info);
                    productService.addItem(newDrink);
                    break;
                case "medicines":
                    Product newMedicine = new medicines(name, quantity, id, price, info);
                    productService.addItem(newMedicine);
                    break;
                case "generals":
                    Product newGeneral = new generals(name, quantity, id, price, info);
                    productService.addItem(newGeneral);
                    break;
                case "clothes":
                    Product newClothe = new clothes(name, quantity, id, price, info);
                    productService.addItem(newClothe);
                    break;
            
                default:
                    break;
            }
        
        return "redirect:/stock";
    }

    @PostMapping("/edit-product")
    public String editProduct(@RequestParam("name") String name,
        @RequestParam("id") String id,
        @RequestParam("category") String category,
        @RequestParam("info") String info,
        @RequestParam("quantity") int quantity,
        @RequestParam("price") double price) {
            switch (category) {
                case "foods":
                    Product updatedFood = new foods(name, quantity, id, price, info);
                    productService.editItem(updatedFood);
                    break;
                case "drinks":
                    Product updatedDrink = new drinks(name, quantity, id, price, info);
                    productService.editItem(updatedDrink);
                    break;
                case "medicines":
                    Product updatedMedicine = new medicines(name, quantity, id, price, info);
                    productService.editItem(updatedMedicine);
                    break;
                case "generals":
                    Product updatedGeneral = new generals(name, quantity, id, price, info);
                    productService.editItem(updatedGeneral);
                    break;
                case "clothes":
                    Product updatedClothe = new clothes(name, quantity, id, price, info);
                    productService.editItem(updatedClothe);
                    break;
            
                default:
                    break;
            }
        return "redirect:/stock";
    }

    @PostMapping("/remove-product")
    public String removeProduct(@RequestParam("id") String id, RedirectAttributes redirectAttrs) {
        productService.getAllItems().removeIf(product -> product.getId().equals(id));
        return "redirect:/stock";
    }

}