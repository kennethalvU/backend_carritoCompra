package com.cuscatlan.carritoCompras.controller;

import com.cuscatlan.carritoCompras.exception.ErrorResponse;
import com.cuscatlan.carritoCompras.exception.OrderException;
import com.cuscatlan.carritoCompras.model.Order;
import com.cuscatlan.carritoCompras.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Object> createOrder(@RequestBody Order order) {
        try {
            Order createdOrder = orderService.createOrder(order);
            return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
        } catch (OrderException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), 400);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}