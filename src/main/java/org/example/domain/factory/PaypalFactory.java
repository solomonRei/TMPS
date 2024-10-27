package org.example.domain.factory;

import org.example.services.DiscountService;
import org.example.services.PaymentService;
import org.example.services.impl.PaypalDiscountImpl;
import org.example.services.impl.PaypalPaymentImpl;

public class PaypalFactory implements PaymentFactory {

    @Override
    public PaymentService createPaymentService() {
        return new PaypalPaymentImpl();
    }

    @Override
    public DiscountService createDiscountService() {
        return new PaypalDiscountImpl();
    }
}
