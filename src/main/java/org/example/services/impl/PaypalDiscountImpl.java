package org.example.services.impl;

import org.example.domain.loggerChain.LogLevel;
import org.example.domain.loggerChain.LoggerChain;
import org.example.services.DiscountService;

import java.math.BigDecimal;

public class PaypalDiscountImpl implements DiscountService {
    private static final Double DISCOUNT = 0.1;

    @Override
    public BigDecimal applyDiscount(BigDecimal amount) {
        LoggerChain.getLoggerChain().log("Applying Paypal discount to " + amount, LogLevel.INFO);
        return amount.multiply(BigDecimal.valueOf(DISCOUNT));
    }
}
