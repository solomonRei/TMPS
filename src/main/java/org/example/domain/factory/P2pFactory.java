package org.example.domain.factory;

import org.example.services.DiscountService;
import org.example.services.PaymentService;
import org.example.services.impl.P2pDiscountImpl;
import org.example.services.impl.P2pPaymentImpl;

public class P2pFactory implements PaymentFactory {

    @Override
    public PaymentService createPaymentService() {
        return new P2pPaymentImpl();
    }

    @Override
    public DiscountService createDiscountService() {
        return new P2pDiscountImpl();
    }
}
