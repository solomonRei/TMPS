package org.example.domain.decorators;

import org.example.services.DiscountService;

import java.math.BigDecimal;

public class LoyaltyDiscountDecorator extends DiscountServiceDecorator {
    private final BigDecimal loyaltyDiscount;

    public LoyaltyDiscountDecorator(DiscountService discountService, BigDecimal loyaltyDiscount) {
        super(discountService);
        this.loyaltyDiscount = loyaltyDiscount;
    }

    @Override
    public BigDecimal applyDiscount(BigDecimal amount) {
        BigDecimal discountedAmount = super.applyDiscount(amount);
        System.out.println("loyalty discount: " + loyaltyDiscount);
        return discountedAmount.subtract(discountedAmount.multiply(loyaltyDiscount));
    }
}
