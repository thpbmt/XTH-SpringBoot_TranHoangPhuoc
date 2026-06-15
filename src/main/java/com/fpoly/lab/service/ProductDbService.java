package com.fpoly.lab.service;

import com.fpoly.lab.model.ProductDb;
import com.fpoly.lab.repository.ProductDbRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;
import java.util.List;
import java.util.Optional;

@Service
public class ProductDbService {

    private final ProductDbRepository productDbRepository;

    public ProductDbService(
            ProductDbRepository productDbRepository) {

        this.productDbRepository = productDbRepository;
    }

    public List<ProductDb> getAllProducts() {
        return productDbRepository.findAll();
    }

    public Optional<ProductDb> getProductById(Long id) {
        return productDbRepository.findById(id);
    }

    public ProductDb addProduct(ProductDb product) {
        return productDbRepository.save(product);
    }

    public Page<ProductDb> getProductsWithPagination(
        int page,
        int size,
        String sortBy) {

    Pageable pageable =
            PageRequest.of(
                    page,
                    size,
                    Sort.by(sortBy).ascending()
            );

    return productDbRepository.findAll(pageable);
}
}
