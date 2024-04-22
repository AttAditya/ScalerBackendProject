package com.scaler.productservicesst.controllers;

import com.scaler.productservicesst.models.Category;
import com.scaler.productservicesst.models.Product;
import com.scaler.productservicesst.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {
    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;

    @Test
    void testValidProductId() {
        Category category = new Category();
        category.setId(1L);
        category.setTitle("Category Title");
        category.setDescription("Category Description");

        Product product = new Product();
        product.setId(1L);
        product.setTitle("Product Title");
        product.setDescription("Product Description");
        product.setPrice(12.34);
        product.setImage("Product Image");
        product.setCategory(category);

        when(productService.getProductById(1L)).thenReturn(product);

        Product output = productController.getProductById(1L);
        assertEquals(product, output);
    }

}