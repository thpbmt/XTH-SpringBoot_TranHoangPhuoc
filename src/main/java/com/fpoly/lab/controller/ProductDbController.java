package com.fpoly.lab.controller;

import com.fpoly.lab.model.ProductDb;
import com.fpoly.lab.service.ProductDbService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.util.List;

@RestController
@RequestMapping("/db/products")
public class ProductDbController {

    private final ProductDbService productDbService;

    public ProductDbController(
            ProductDbService productDbService) {

        this.productDbService = productDbService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDb>> getProducts() {

        return ResponseEntity.ok(
                productDbService.getAllProducts()
        );
    }

    @PostMapping
    public ResponseEntity<ProductDb> saveProduct(
             @Valid@RequestBody ProductDb product) {

        return ResponseEntity.ok(
                productDbService.addProduct(product)
        );
    }

    @GetMapping("/page")
public ResponseEntity<Page<ProductDb>> getProductsPage(

        @RequestParam(defaultValue = "0")
        int page,

        @RequestParam(defaultValue = "5")
        int size,

        @RequestParam(defaultValue = "id")
        String sortBy
) {

    return ResponseEntity.ok(
            productDbService.getProductsWithPagination(
                    page,
                    size,
                    sortBy
            )
    );
}
}
