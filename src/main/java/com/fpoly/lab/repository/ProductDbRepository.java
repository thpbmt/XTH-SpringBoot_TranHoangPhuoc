package com.fpoly.lab.repository;

import com.fpoly.lab.model.ProductDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDbRepository
        extends JpaRepository<ProductDb, Long> {

    List<ProductDb> findByName(String name);

    List<ProductDb> findByPriceGreaterThan(Double price);
}
