package org.example.services.impl;

import org.example.services.DiscountService;

import java.math.BigDecimal;

public class PaypalDiscountImpl implements DiscountService {
    private static final Double DISCOUNT = 0.1;

    @Override
    public BigDecimal applyDiscount(BigDecimal amount) {
        System.out.println("Applying Paypal discount to " + amount);
        return amount.multiply(BigDecimal.valueOf(DISCOUNT));
    }
}
