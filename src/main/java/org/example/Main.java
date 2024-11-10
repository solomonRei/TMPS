package org.example;

import org.example.domain.adapters.DomesticTaxAPI;
import org.example.domain.adapters.DomesticTaxAdapter;
import org.example.domain.adapters.TaxCalculator;
import org.example.domain.factory.P2pFactory;
import org.example.domain.factory.PaymentFactory;
import org.example.domain.factory.PaypalFactory;
import org.example.domain.proxy.DBConnectionPool;
import org.example.services.DiscountService;
import org.example.services.InventoryService;
import org.example.services.NotificationService;
import org.example.services.OrderService;
import org.example.services.PaymentService;
import org.example.services.impl.InventoryServiceImpl;
import org.example.services.impl.NotificationServiceImpl;
import org.example.services.impl.OrderServiceImpl;
import org.example.utils.OrderFacade;
import org.example.utils.OrderUtils;

public class Main {

    public static void main(String[] args) {
        InventoryService inventoryService = new InventoryServiceImpl();
        NotificationService notificationService = new NotificationServiceImpl();

        TaxCalculator domesticTaxCalculator = new DomesticTaxAdapter(new DomesticTaxAPI());

        PaymentFactory paymentFactoryP2p = new P2pFactory();
        PaymentService paymentServiceP2p = paymentFactoryP2p.createPaymentService();
        DiscountService discountServiceP2p = paymentFactoryP2p.createDiscountService();

        PaymentFactory paymentFactoryPayPal = new PaypalFactory();
        PaymentService paymentServicePayPal = paymentFactoryPayPal.createPaymentService();
        DiscountService discountServicePayPal = paymentFactoryPayPal.createDiscountService();


        DBConnectionPool connectionPool = DBConnectionPool.getInstance(2);

        // with Facade
        OrderService orderService1 = new OrderServiceImpl(paymentServicePayPal, discountServicePayPal, connectionPool,
                domesticTaxCalculator);
        OrderFacade orderFacade = new OrderFacade(inventoryService, orderService1, notificationService);
        orderFacade.placeOrder(OrderUtils.generateOrder());

        OrderService orderService2 = new OrderServiceImpl(paymentServiceP2p, discountServiceP2p, connectionPool,
                domesticTaxCalculator);
        System.out.println("\nProcessing order with P2P payment");
        orderService2.processOrder(OrderUtils.generateOrder());

        connectionPool.closeAllConnections();
    }
}