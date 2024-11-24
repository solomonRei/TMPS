package org.example.services.impl;

import org.example.domain.loggerChain.LogLevel;
import org.example.domain.loggerChain.LoggerChain;
import org.example.services.PaymentService;

import java.math.BigDecimal;

public class PaypalPaymentImpl implements PaymentService {

    @Override
    public void pay(BigDecimal amount) {
        LoggerChain.getLoggerChain().log("Paying " + amount + " using Paypal", LogLevel.INFO);
    }
}
