package com.scaler.productservicesst.exceptions;

import lombok.Getter;

@Getter
public class CategoryNotFoundException extends RuntimeException {
    private final Long id;

    public CategoryNotFoundException(Long id, String message) {
        super(message);
        this.id = id;
    }
}
