package org.example.services.impl;

import org.example.services.PaymentService;

import java.math.BigDecimal;

public class PaypalPaymentImpl implements PaymentService {

    @Override
    public void pay(BigDecimal amount) {
        System.out.println("Paying " + amount + " using Paypal");
    }
}
