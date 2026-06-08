package com.fpoly.lab.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fpoly.lab.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final List<Product> productList = new ArrayList<>();

    public ProductController() {
        productList.add(new Product(1L, "Laptop Acer Nitro", 18500000.0));
        productList.add(new Product(2L, "Chuột Logitech G102", 400000.0));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productList);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        for (Product p : productList) {
            if (p.getId().equals(id)) {
                return ResponseEntity.ok(p);
            }
        }
        return ResponseEntity.notFound().build();
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        productList.add(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @RequestBody Product updatedProduct) {

        for (Product p : productList) {
            if (p.getId().equals(id)) {
                p.setName(updatedProduct.getName());
                p.setPrice(updatedProduct.getPrice());

                return ResponseEntity.ok(p);
            }
        }

        return ResponseEntity.notFound().build();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {

        Iterator<Product> iterator = productList.iterator();

        while (iterator.hasNext()) {
            Product p = iterator.next();

            if (p.getId().equals(id)) {
                iterator.remove();
                return ResponseEntity.ok("Xóa thành công!");
            }
        }

        return ResponseEntity.notFound().build();
    }

    // TOP N PRODUCTS
    @GetMapping("/top")
    public ResponseEntity<List<Product>> getTopProducts(
            @RequestParam(defaultValue = "5") int n) {

        List<Product> topProducts = productList.stream()
                .sorted((p1, p2) ->
                        Double.compare(p2.getPrice(), p1.getPrice()))
                .limit(n)
                .collect(Collectors.toList());

        return ResponseEntity.ok(topProducts);
    }

    // STATISTICS
    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Object>> getStatistics() {

        Map<String, Object> stats = new HashMap<>();

        int total = productList.size();

        double avgPrice = productList.stream()
                .mapToDouble(Product::getPrice)
                .average()
                .orElse(0);

        double maxPrice = productList.stream()
                .mapToDouble(Product::getPrice)
                .max()
                .orElse(0);

        double minPrice = productList.stream()
                .mapToDouble(Product::getPrice)
                .min()
                .orElse(0);

        stats.put("totalProducts", total);
        stats.put("averagePrice", avgPrice);
        stats.put("maxPrice", maxPrice);
        stats.put("minPrice", minPrice);

        return ResponseEntity.ok(stats);
    }
}
