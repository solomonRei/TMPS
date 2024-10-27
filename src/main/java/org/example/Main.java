package org.example;

import org.example.domain.factory.P2pFactory;
import org.example.domain.factory.PaymentFactory;
import org.example.domain.factory.PaypalFactory;
import org.example.services.DiscountService;
import org.example.services.impl.OrderServiceImpl;
import org.example.services.OrderService;
import org.example.services.PaymentService;
import org.example.utils.DBConnectionPool;
import org.example.utils.OrderUtils;

public class Main {

    public static void main(String[] args) {
        PaymentFactory paymentFactoryP2p = new P2pFactory();
        PaymentService paymentServiceP2p = paymentFactoryP2p.createPaymentService();
        DiscountService discountServiceP2p = paymentFactoryP2p.createDiscountService();

        PaymentFactory paymentFactoryPayPal = new PaypalFactory();
        PaymentService paymentServicePayPal = paymentFactoryPayPal.createPaymentService();
        DiscountService discountServicePayPal = paymentFactoryPayPal.createDiscountService();

        DBConnectionPool connectionPool = DBConnectionPool.getInstance(2);

        OrderService orderService1 = new OrderServiceImpl(paymentServicePayPal, discountServicePayPal, connectionPool);
        System.out.println("\nProcessing order with Paypal payment");
        orderService1.processOrder(OrderUtils.generateOrder());

        OrderService orderService2 = new OrderServiceImpl(paymentServiceP2p, discountServiceP2p, connectionPool);
        System.out.println("\nProcessing order with P2P payment");
        orderService2.processOrder(OrderUtils.generateOrder());

        connectionPool.closeAllConnections();
    }
}