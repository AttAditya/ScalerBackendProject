package com.scaler.productservicesst.services;

import com.scaler.productservicesst.exceptions.CategoryNotFoundException;
import com.scaler.productservicesst.exceptions.ProductNotFoundException;
import com.scaler.productservicesst.models.Category;
import com.scaler.productservicesst.models.Product;
import com.scaler.productservicesst.repositories.CategoryRepository;
import com.scaler.productservicesst.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@Service("SelfProductService")
public class SelfProductService implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException(id, "Product not found");
        }

        return optionalProduct.get();
    }

    @Override
    public Product createProduct(Product product) {
        Category category = product.getCategory();

        if (category.getId() == null) {
            Category savedCategory = categoryRepository.save(category);
            product.setCategory(savedCategory);
        } else {
            Optional<Category> existingCategory = categoryRepository.findById(category.getId());

            if (existingCategory.isEmpty()) {
                throw new CategoryNotFoundException(category.getId(), "Category not found");
            }

            product.setCategory(existingCategory.get());
        }

        return productRepository.save(product);
    }
}
