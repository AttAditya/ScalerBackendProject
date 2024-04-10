package com.scaler.productservicesst.repositories;

import com.scaler.productservicesst.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Override
    Category save(Category category);
}
