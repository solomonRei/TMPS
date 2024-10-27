package org.example.services.impl;

import org.example.services.DiscountService;

import java.math.BigDecimal;

public class P2pDiscountImpl implements DiscountService {
    private static final Double DISCOUNT = 0.15;

    @Override
    public BigDecimal applyDiscount(BigDecimal amount) {
        System.out.println("Applying P2P discount to " + amount);
        return amount.multiply(BigDecimal.valueOf(DISCOUNT));
    }
}
