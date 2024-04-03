package com.scaler.productservicesst.services;

import com.scaler.productservicesst.dtos.FakeStoreProductDTO;
import com.scaler.productservicesst.models.Category;
import com.scaler.productservicesst.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {
    private Product convertFakeStoreProductDTOToProduct(FakeStoreProductDTO fakeStoreProductDTO) {
        // Convert FakeStore Product DTO to Product object
        Product product = new Product();

        product.setId(fakeStoreProductDTO.getId());
        product.setTitle(fakeStoreProductDTO.getTitle());
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setPrice(fakeStoreProductDTO.getPrice());

        Category category = new Category();
        category.setId(null);
        category.setTitle(null);
        category.setDescription(fakeStoreProductDTO.getCategory());

        product.setCategory(category);
        product.setImage(fakeStoreProductDTO.getImage());

        return product;
    }

    @Override
    public Product getProductById(Long id) {
        // Call the FakeStore API to get the product with given id.
        RestTemplate restTemplate = new RestTemplate();
        FakeStoreProductDTO fakeStoreProductDTO = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDTO.class);

        // Handle Exception for products that don't exist
        if (fakeStoreProductDTO == null) {
            return null;
        }

        return convertFakeStoreProductDTOToProduct(fakeStoreProductDTO);
    }

    @Override
    public List<Product> getAllProducts() {
        // Call the FakeStore API to get all products
        RestTemplate restTemplate = new RestTemplate();
        FakeStoreProductDTO[] fakeStoreProductDTOs = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDTO[].class);

        List<Product> products = new ArrayList<>();

        // Handle Exception that products don't exist
        if (fakeStoreProductDTOs == null) {
            return products;
        }

        // Convert All FakeStore Product DTOs to Product
        for (FakeStoreProductDTO fakeStoreProductDTO: fakeStoreProductDTOs) {
            products.add(convertFakeStoreProductDTOToProduct(fakeStoreProductDTO));
        }

        return products;
    }
}
