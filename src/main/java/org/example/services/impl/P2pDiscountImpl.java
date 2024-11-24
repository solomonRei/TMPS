package org.example.services.impl;

import org.example.domain.loggerChain.LogLevel;
import org.example.domain.loggerChain.LoggerChain;
import org.example.services.DiscountService;

import java.math.BigDecimal;

public class P2pDiscountImpl implements DiscountService {
    private static final Double DISCOUNT = 0.15;

    @Override
    public BigDecimal applyDiscount(BigDecimal amount) {
        LoggerChain.getLoggerChain().log("Applying P2P discount to " + amount, LogLevel.INFO);
        return amount.multiply(BigDecimal.valueOf(DISCOUNT));
    }
}
