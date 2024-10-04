package org.example;

import org.example.payments.P2pPaymentImpl;
import org.example.payments.PaypalPaymentImpl;
import org.example.utils.OrderUtils;

public class Main {

    public static void main(String[] args) {
        OrderService orderService1 = new OrderService(new PaypalPaymentImpl());
        System.out.println("Processing order with Paypal payment");
        orderService1.processOrder(OrderUtils.generateOrder());

        OrderService orderService2 = new OrderService(new P2pPaymentImpl());
        System.out.println("Processing order with P2P payment");
        orderService2.processOrder(OrderUtils.generateOrder());
    }
}