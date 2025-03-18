package com.cuscatlan.carritoCompras.projection;

public interface PaymentProjection {

    Long getId();
    Long getIdCustomer();
    String getCustomerName();
    String getStatus();
    double getTotalPrice();

}