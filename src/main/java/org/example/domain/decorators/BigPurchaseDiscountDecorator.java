package org.example.domain.decorators;

import org.example.services.DiscountService;

import java.math.BigDecimal;

public class BigPurchaseDiscountDecorator extends DiscountServiceDecorator {
    private final BigDecimal bulkDiscount;
    private final BigDecimal threshold;

    public BigPurchaseDiscountDecorator(DiscountService discountService, BigDecimal bulkDiscount, BigDecimal threshold) {
        super(discountService);
        this.bulkDiscount = bulkDiscount;
        this.threshold = threshold;
    }

    @Override
    public BigDecimal applyDiscount(BigDecimal amount) {
        BigDecimal discountedAmount = super.applyDiscount(amount);
        if (amount.compareTo(threshold) > 0) {
            System.out.println("Discount for big orders: " + bulkDiscount);
            return discountedAmount.subtract(discountedAmount.multiply(bulkDiscount));
        }
        return discountedAmount;
    }
}
