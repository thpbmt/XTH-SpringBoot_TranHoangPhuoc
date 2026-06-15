package com.fpoly.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpoly.lab.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    
}
