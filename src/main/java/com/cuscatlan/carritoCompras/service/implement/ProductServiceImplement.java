package com.cuscatlan.carritoCompras.service.implement;

import com.cuscatlan.carritoCompras.dto.ProductDto;
import com.cuscatlan.carritoCompras.exception.ProductNotFoundException;
import com.cuscatlan.carritoCompras.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductServiceImplement implements ProductService {

    private final String urlApi = "https://fakestoreapi.com/products";
    private RestTemplate restTemplate;

    public ProductServiceImplement(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<ProductDto> getAllProducts() {

        try {
            ProductDto[] products = restTemplate.getForObject(urlApi, ProductDto[].class);
            if (products == null || products.length == 0) {
                throw new ProductNotFoundException("Product not found");
            }
            return Arrays.asList(products);
        } catch (Exception e) {
            throw new ProductNotFoundException("Error fetching products: " + e.getMessage());
        }
    }

}