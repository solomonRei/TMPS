package org.example.payments;

import java.math.BigDecimal;

public class PaypalPaymentImpl implements PaymentService {

    @Override
    public void pay(BigDecimal amount) {
        System.out.println("Paying " + amount + " using Paypal");
    }
}
