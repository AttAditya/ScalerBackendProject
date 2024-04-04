package com.scaler.productservicesst.exceptions;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends RuntimeException {
    private final Long id;

    public ProductNotFoundException(Long id, String message) {
        super(message);
        this.id = id;
    }
}
