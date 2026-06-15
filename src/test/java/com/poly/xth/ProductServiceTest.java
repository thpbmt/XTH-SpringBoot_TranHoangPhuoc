package com.poly.xth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.fpoly.lab.model.Product;
import com.fpoly.lab.repository.ProductRepository;
import com.fpoly.lab.service.ProductService;

public class ProductServiceTest {
       private final ProductRepository repository =
            new ProductRepository();

    private final ProductService service =
            new ProductService(repository);

    @Test
    void testFindProductFound() {

        assertTrue(
                service.getProductById(1L).isPresent()
        );
    }

    @Test
    void testFindProductNotFound() {

        assertFalse(
                service.getProductById(999L).isPresent()
        );
    }

    @Test
    void testAddProductSuccess() {

        Product product =
                new Product(
                        3L,
                        "Bàn phím AKKO",
                        1200000.0
                );

        Product saved =
                service.addProduct(product);

        assertEquals(
                "Bàn phím AKKO",
                saved.getName()
        );
    }
}
