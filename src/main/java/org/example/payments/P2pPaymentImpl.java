package org.example.payments;

import java.math.BigDecimal;

public class P2pPaymentImpl implements PaymentService {

    @Override
    public void pay(BigDecimal amount) {
        System.out.println("Paying " + amount + " using P2P");
    }
}
