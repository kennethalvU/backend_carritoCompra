package com.cuscatlan.carritoCompras.service.implement;

import com.cuscatlan.carritoCompras.model.Order;
import com.cuscatlan.carritoCompras.model.Payment;
import com.cuscatlan.carritoCompras.projection.PaymentProjection;
import com.cuscatlan.carritoCompras.repository.OrderRepository;
import com.cuscatlan.carritoCompras.repository.PaymentRepository;
import com.cuscatlan.carritoCompras.service.PaymentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentServiceImplement implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    @Override
    public String createPayment(Long idCustomer) {

        Optional<PaymentProjection> dataOrder = paymentRepository.findByCustomerIdOrder(idCustomer);

        if (dataOrder.isPresent()) {
            PaymentProjection paymentProjection = dataOrder.get();

            System.out.println("Order ID: " + paymentProjection.getId());
            System.out.println("Customer ID: " + paymentProjection.getIdCustomer());
            System.out.println("Total Price: " + paymentProjection.getTotalPrice());
            System.out.println("Order Status: " + paymentProjection.getStatus());

            Payment payment = new Payment();
            payment.setIdCustomer(paymentProjection.getIdCustomer());
            payment.setIdOrder(paymentProjection.getId());
            payment.setPaymentMethod("Credit Card");

            paymentRepository.save(payment);

            Order orderToUpdate = orderRepository.findById(paymentProjection.getId()).get();
            orderToUpdate.setStatus("PROCESSED");
            orderRepository.save(orderToUpdate);

            return "Payment processed successfully with amount: " + paymentProjection.getTotalPrice();
        } else {
            System.out.println("No 'In Processing' order found for customer ID: " + idCustomer);
            return "No order found for this customer with status 'In Processing'.";
        }
    }
}