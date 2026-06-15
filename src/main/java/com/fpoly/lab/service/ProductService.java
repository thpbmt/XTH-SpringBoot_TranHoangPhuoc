package com.fpoly.lab.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fpoly.lab.model.Product;
import com.fpoly.lab.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product addProduct(Product product) {

        if (product.getPrice() < 0) {
            throw new IllegalArgumentException(
                    "Giá sản phẩm không được âm");
        }

        productRepository.save(product);

        return product;
    }

    public boolean deleteProduct(Long id) {
        return productRepository.deleteById(id);
    }
}
