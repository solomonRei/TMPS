package org.example.domain.decorators;

import org.example.services.DiscountService;

import java.math.BigDecimal;

public abstract class DiscountServiceDecorator implements DiscountService {
    protected final DiscountService discountService;

    public DiscountServiceDecorator(DiscountService discountService) {
        this.discountService = discountService;
    }

    @Override
    public BigDecimal applyDiscount(BigDecimal amount) {
        return discountService.applyDiscount(amount);
    }
}
