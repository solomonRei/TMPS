package org.example.domain.adapters;

import java.math.BigDecimal;

public class InternationalTaxAdapter implements TaxCalculator {
    private final InternationalTaxAPI internationalTaxAPI;
    private final String country;

    public InternationalTaxAdapter(InternationalTaxAPI internationalTaxAPI, String country) {
        this.internationalTaxAPI = internationalTaxAPI;
        this.country = country;
    }

    @Override
    public BigDecimal calculateTax(BigDecimal amount) {
        return internationalTaxAPI.calculateGlobalTax(amount, country);
    }
}

