package com.cuscatlan.carritoCompras.repository;

import com.cuscatlan.carritoCompras.model.Payment;
import com.cuscatlan.carritoCompras.projection.PaymentProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query(value = "SELECT * FROM orders o WHERE o.id_customer = ? AND o.status = 'IN_PROCESSING' ORDER BY o.id DESC FETCH FIRST 1 ROW ONLY", nativeQuery = true)
    public Optional<PaymentProjection> findByCustomerIdOrder(Long idCustomer);

}