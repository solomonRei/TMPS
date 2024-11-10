package org.example.domain.adapters;

import java.math.BigDecimal;

public class DomesticTaxAPI {
    public BigDecimal computeLocalTax(BigDecimal amount) {
        System.out.println("Calculating tax using DomesticTaxAPI...");
        return amount.multiply(BigDecimal.valueOf(0.18)); // 18% налог
    }
}
