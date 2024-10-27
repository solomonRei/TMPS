package org.example.services.impl;

import org.example.domain.models.Order;
import org.example.domain.models.Product;
import org.example.services.DiscountService;
import org.example.services.OrderService;
import org.example.services.PaymentService;
import org.example.utils.DBConnect;
import org.example.utils.DBConnectionPool;

import java.math.BigDecimal;
import java.sql.ResultSet;

public class OrderServiceImpl implements OrderService {

    private final PaymentService paymentService;
    private final DiscountService discountService;
    private final DBConnectionPool dbConnectionPool;

    public OrderServiceImpl(PaymentService paymentService, DiscountService discountService, DBConnectionPool dbConnectionPool) {
        this.paymentService = paymentService;
        this.discountService = discountService;
        this.dbConnectionPool = dbConnectionPool;
    }

    public void processOrder(Order order) {
        // Just for demonstration purposes
        getProductList();

        BigDecimal amount = order.getProductList().stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal discount = discountService.applyDiscount(amount);
        BigDecimal finalAmount = amount.subtract(discount);

        paymentService.pay(finalAmount);

        Order managerOrder = new Order(order, "Manager Invoice");
        Order userOrder = new Order(order, "User Invoice");

        System.out.println("Original Order Description: " + order);
        System.out.println("Manager Order Description: " + managerOrder);
        System.out.println("User Order Description: " + userOrder);
    }

    private void getProductList() {
        DBConnect conn = dbConnectionPool.getConnection();
        ResultSet rs = conn.executeQuery("SELECT * FROM products");
        dbConnectionPool.returnConnectionBack(conn);
    }
}
