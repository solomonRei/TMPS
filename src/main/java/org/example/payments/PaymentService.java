package org.example.payments;

import java.math.BigDecimal;

public interface PaymentService {

    void pay(BigDecimal amount);

}
