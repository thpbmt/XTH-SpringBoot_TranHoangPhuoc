package com.fpoly.lab.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fpoly.lab.dto.ProductDTO;
import com.fpoly.lab.model.Product;
import com.fpoly.lab.service.ProductBackupService;
import com.fpoly.lab.service.ProductDbService;
import com.fpoly.lab.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    private final ProductBackupService backupService;
    private ProductDbService productDbService;

    public ProductController(ProductService productService, ProductDbService productDbService, ProductBackupService backupService) {
        this.productService = productService;
        this.productDbService = productDbService;
        this.backupService = backupService;
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(
                productService.getAllProducts());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(
            @PathVariable Long id) {

        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Product> saveProduct(
            @RequestBody Product product) {

        Product savedProduct = productService.addProduct(product);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedProduct);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable Long id) {

        boolean deleted = productService.deleteProduct(id);

        if (deleted) {
            return ResponseEntity.ok("Xóa thành công");
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/dto/{id}")
    public ResponseEntity<ProductDTO> getDto(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                backupService.getProductDtoInfo(id));

    }
}
