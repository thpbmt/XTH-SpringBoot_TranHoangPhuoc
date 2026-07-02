package com.fpoly.lab.service;

import org.springframework.stereotype.Service;

import com.fpoly.lab.dto.ProductDTO;
import com.fpoly.lab.model.ProductDb;
import com.fpoly.lab.repository.ProductDbRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductBackupService {

    private final ProductDbRepository repository;

    public ProductBackupService(ProductDbRepository repository) {
        this.repository = repository;
    }

    public ProductDTO getProductDtoInfo(Long id) {

        log.info("Đang tìm sản phẩm có id = {}", id);

        ProductDb product = repository.findById(id)
                .orElseThrow(() -> {

                    log.error("Không tìm thấy sản phẩm {}", id);

                    return new RuntimeException("Product not found");
                });

        ProductDTO dto = new ProductDTO();

        dto.setName(product.getName());

        dto.setPrice(product.getPrice());

        log.info("Đã convert Entity -> DTO");

        return dto;

    }

}
