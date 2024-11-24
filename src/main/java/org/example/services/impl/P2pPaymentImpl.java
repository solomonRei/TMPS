package org.example.services.impl;

import org.example.domain.loggerChain.LogLevel;
import org.example.domain.loggerChain.LoggerChain;
import org.example.services.PaymentService;

import java.math.BigDecimal;

public class P2pPaymentImpl implements PaymentService {

    @Override
    public void pay(BigDecimal amount) {
        LoggerChain.getLoggerChain().log("Paying " + amount + " using P2P", LogLevel.INFO);
    }
}
