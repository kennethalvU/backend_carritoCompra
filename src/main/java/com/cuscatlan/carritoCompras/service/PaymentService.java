package com.cuscatlan.carritoCompras.service;

import com.cuscatlan.carritoCompras.dto.PaymentDto;
import com.cuscatlan.carritoCompras.model.Payment;

import java.util.Optional;

public interface PaymentService {

    String createPayment(Long idCustomer);
    //Optional<PaymentDto>  getPaymentById(Optional<PaymentDto> dataRegistro);
}