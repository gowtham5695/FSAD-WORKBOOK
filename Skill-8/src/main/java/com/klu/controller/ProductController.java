package com.klu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klu.model.Product;
import com.klu.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository repo;

    // POST - Add product
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return repo.save(product);
    }

    // Get by category
    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category) {
        return repo.findByCategory(category);
    }

    // Filter by price
    @GetMapping("/filter")
    public List<Product> filterProducts(@RequestParam double min,
                                        @RequestParam double max) {
        return repo.findByPriceBetween(min, max);
    }

    // Sort products
    @GetMapping("/sorted")
    public List<Product> sortProducts() {
        return repo.sortByPrice();
    }

    // Expensive products
    @GetMapping("/expensive/{price}")
    public List<Product> expensiveProducts(@PathVariable double price) {
        return repo.findExpensiveProducts(price);
    }
}