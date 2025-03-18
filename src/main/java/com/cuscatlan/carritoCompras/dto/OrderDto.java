package com.cuscatlan.carritoCompras.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDto {

    private Long id;
    private Long idCustomer;
    private String status;
    private List<ProductDto> products;

}
