package com.cuscatlan.carritoCompras.repository;

import com.cuscatlan.carritoCompras.model.Order;
import com.cuscatlan.carritoCompras.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
