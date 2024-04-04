package com.scaler.productservicesst.services;

import com.scaler.productservicesst.dtos.FakeStoreProductDTO;
import com.scaler.productservicesst.exceptions.ProductNotFoundException;
import com.scaler.productservicesst.models.Category;
import com.scaler.productservicesst.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {
    private Product convertFakeStoreProductDTOToProduct(FakeStoreProductDTO fakeStoreProductDTO) {
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
        RestTemplate restTemplate = new RestTemplate();
        FakeStoreProductDTO fakeStoreProductDTO = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDTO.class);

        if (fakeStoreProductDTO == null) {
            throw new ProductNotFoundException(id, "Product ID not found: " + id);
        }

        return convertFakeStoreProductDTOToProduct(fakeStoreProductDTO);
    }

    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = new RestTemplate();
        FakeStoreProductDTO[] fakeStoreProductDTOs = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDTO[].class);

        List<Product> products = new ArrayList<>();

        if (fakeStoreProductDTOs == null) {
            return products;
        }

        for (FakeStoreProductDTO fakeStoreProductDTO: fakeStoreProductDTOs) {
            products.add(convertFakeStoreProductDTOToProduct(fakeStoreProductDTO));
        }

        return products;
    }
}
