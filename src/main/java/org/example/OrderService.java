package org.example;

import org.example.payments.PaymentService;

import java.math.BigDecimal;

public class OrderService {

    private final PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    public void processOrder(Order order) {
        BigDecimal amount = order.getProductList().stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        paymentService.pay(amount);
    }

}
