package org.example.services;

import java.math.BigDecimal;

public interface DiscountService {
    BigDecimal applyDiscount(BigDecimal amount);
}
