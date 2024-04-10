package com.scaler.productservicesst.exceptionhandlers;

import com.scaler.productservicesst.dtos.ExceptionDTO;
import com.scaler.productservicesst.exceptions.CategoryNotFoundException;
import com.scaler.productservicesst.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDTO> handleGeneralException() {
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setMessage("Unknown Error Occurred");
        exceptionDTO.setResolution(null);

        return new ResponseEntity<>(exceptionDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleProductNotFoundException(ProductNotFoundException exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setMessage("Product ID not found: " + exception.getId());
        exceptionDTO.setResolution("ProductNotFoundException caught");

        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleCategoryNotFoundException(CategoryNotFoundException exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setMessage("Category ID not found: " + exception.getId());
        exceptionDTO.setResolution("CategoryNotFoundException caught");

        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }
}
