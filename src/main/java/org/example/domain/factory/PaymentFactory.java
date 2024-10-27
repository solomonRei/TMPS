package org.example.domain.factory;

import org.example.services.DiscountService;
import org.example.services.PaymentService;

public interface PaymentFactory {
    PaymentService createPaymentService();
    DiscountService createDiscountService();
}
