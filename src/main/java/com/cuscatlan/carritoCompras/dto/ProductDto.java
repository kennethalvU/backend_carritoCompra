package com.cuscatlan.carritoCompras.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String image;
}