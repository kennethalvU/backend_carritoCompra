package com.cuscatlan.carritoCompras.service.implement;

import com.cuscatlan.carritoCompras.exception.OrderException;
import com.cuscatlan.carritoCompras.model.Order;
import com.cuscatlan.carritoCompras.model.Product;
import com.cuscatlan.carritoCompras.repository.OrderRepository;
import com.cuscatlan.carritoCompras.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderServiceImplement implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    @Override
    public Order createOrder(Order order) {

        for (Product product : order.getProducts()) {
            if (product.getPrice() == null || product.getPrice() <= 0) {
                throw new OrderException("Product price cannot be null or less than or equal to 0.");
            }

            /*if(product.getIdProduct().equals(product.getIdProduct())) {
                throw new OrderException("Product with ID " + product.getIdProduct() + " is already in the order.");
            }*/

            product.setOrder(order);
        }

        if (order.getProducts() == null || order.getProducts().isEmpty()) {
            throw new OrderException("An order must contain at least one product.");
        }


        if(orderRepository.findById(order.getId()).isPresent()) {

            Order existingOrder = orderRepository.findById(order.getId()).get();

            if("PROCESSED".equals(existingOrder.getStatus())) {

                throw new OrderException("Products cannot be added to an order that has already been processed.");
            }

            double currentTotalPrice = existingOrder.getTotalPrice();
            double newTotalPrice = order.getProducts().stream().mapToDouble(product -> product.getPrice()).sum();
            double updatetotalPrice = currentTotalPrice + newTotalPrice;

            order.setTotalPrice(updatetotalPrice);

        } else {
            double totalPrice = order.getProducts().stream().mapToDouble(product -> product.getPrice()).sum();
            order.setTotalPrice(totalPrice);
        }
        order.setId(order.getId());
        order.setIdCustomer(order.getIdCustomer());
        order.setStatus("IN_PROCESSING");
        return orderRepository.save(order);
    }
}
