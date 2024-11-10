package org.example.domain.adapters;

import java.math.BigDecimal;

public class InternationalTaxAPI {
    public BigDecimal calculateGlobalTax(BigDecimal amount, String country) {
        System.out.println("Calculating tax using InternationalTaxAPI for country: " + country);
        return amount.multiply(BigDecimal.valueOf(0.25));
    }
}
