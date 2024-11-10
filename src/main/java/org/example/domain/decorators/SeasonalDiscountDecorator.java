package org.example.domain.decorators;

import org.example.services.DiscountService;

import java.math.BigDecimal;

public class SeasonalDiscountDecorator extends DiscountServiceDecorator {
    private final BigDecimal seasonalDiscount;

    public SeasonalDiscountDecorator(DiscountService discountService, BigDecimal seasonalDiscount) {
        super(discountService);
        this.seasonalDiscount = seasonalDiscount;
    }

    @Override
    public BigDecimal applyDiscount(BigDecimal amount) {
        BigDecimal discountedAmount = super.applyDiscount(amount);
        System.out.println("seasonal discount: " + seasonalDiscount);
        return discountedAmount.subtract(discountedAmount.multiply(seasonalDiscount));
    }
}
