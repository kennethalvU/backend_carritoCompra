package com.cuscatlan.carritoCompras.repository;

import com.cuscatlan.carritoCompras.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}