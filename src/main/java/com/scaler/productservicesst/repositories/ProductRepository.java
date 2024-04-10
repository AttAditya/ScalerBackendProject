package com.scaler.productservicesst.repositories;

import com.scaler.productservicesst.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    Product save(Product product);

    @Override
    Optional<Product> findById(Long id);
}
