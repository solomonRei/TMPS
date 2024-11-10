package org.example.domain.adapters;

import java.math.BigDecimal;

public class DomesticTaxAdapter implements TaxCalculator {
    private final DomesticTaxAPI domesticTaxAPI;

    public DomesticTaxAdapter(DomesticTaxAPI domesticTaxAPI) {
        this.domesticTaxAPI = domesticTaxAPI;
    }

    @Override
    public BigDecimal calculateTax(BigDecimal amount) {
        return domesticTaxAPI.computeLocalTax(amount);
    }
}
