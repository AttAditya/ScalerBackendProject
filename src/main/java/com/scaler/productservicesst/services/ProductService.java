package com.scaler.productservicesst.services;

import com.scaler.productservicesst.models.Product;

import java.util.List;

public interface ProductService {
     List<Product> getAllProducts();
     Product getProductById(Long id);
     Product createProduct(Product product);
}
