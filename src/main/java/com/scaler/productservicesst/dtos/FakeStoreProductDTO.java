package com.scaler.productservicesst.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FakeStoreProductDTO {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String category;
    private String image;
}
