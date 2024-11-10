package org.example.services.impl;

import org.example.domain.adapters.TaxCalculator;
import org.example.domain.decorators.BigPurchaseDiscountDecorator;
import org.example.domain.decorators.LoyaltyDiscountDecorator;
import org.example.domain.decorators.SeasonalDiscountDecorator;
import org.example.domain.models.Order;
import org.example.domain.models.Product;
import org.example.domain.proxy.CacheableConnection;
import org.example.domain.proxy.DBConnection;
import org.example.domain.proxy.DBConnectionPool;
import org.example.services.DiscountService;
import org.example.services.OrderService;
import org.example.services.PaymentService;
import org.example.utils.ResultSetUtils;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {

    private final PaymentService paymentService;
    private final DiscountService discountService;
    private final DBConnectionPool dbConnectionPool;
    private final TaxCalculator taxCalculator;

    public OrderServiceImpl(PaymentService paymentService, DiscountService discountService,
                            DBConnectionPool dbConnectionPool, TaxCalculator taxCalculator) {
        this.paymentService = paymentService;
        this.discountService = discountService;
        this.dbConnectionPool = dbConnectionPool;
        this.taxCalculator = taxCalculator;
    }

    public void processOrder(Order order) {
        // Just for demonstration purposes
        getProductList();

        BigDecimal amount = order.getProductList().stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("\n----DISCOUNT CALCULATION ADAPTERS----");
        System.out.println("Total amount before discounts: " + amount);

        DiscountService decoratedDiscount = new BigPurchaseDiscountDecorator(
                new LoyaltyDiscountDecorator(
                        new SeasonalDiscountDecorator(
                                discountService,
                                BigDecimal.valueOf(0.2)
                        ),
                        BigDecimal.valueOf(0.05)
                ),
                BigDecimal.valueOf(0.15),
                BigDecimal.valueOf(500)
        );

        BigDecimal discount = decoratedDiscount.applyDiscount(amount);
        BigDecimal finalAmount = amount.subtract(discount);
        System.out.println("Final discounted amount after discounts: " + finalAmount);

        System.out.println("\n----TAX CALCULATION----");
        BigDecimal tax = taxCalculator.calculateTax(finalAmount);
        BigDecimal totalWithTax = finalAmount.add(tax);
        System.out.println("Tax: " + tax);
        System.out.println("Total amount after tax: " + totalWithTax);

        System.out.println("\n----PAYMENT----");
        paymentService.pay(totalWithTax);

        Order managerOrder = new Order(order, "Manager Invoice");
        Order userOrder = new Order(order, "User Invoice");

        System.out.println("Original Order Description: " + order);
        System.out.println("Manager Order Description: " + managerOrder);
        System.out.println("User Order Description: " + userOrder);
    }

    private void getProductList() {
        DBConnection conn = dbConnectionPool.getConnection();

        System.out.println("------ Getting product list from the database -----\n");

        try {
            ResultSet resultSet1 = conn.executeQuery("SELECT * FROM products");
            System.out.println("First query results:");
            if (resultSet1 != null) {
                List<Map<String, Object>> rows = ResultSetUtils.extractData(resultSet1);
                ResultSetUtils.printCachedResultSet(rows);
            }

            if (conn instanceof CacheableConnection cacheableConn) {
                List<Map<String, Object>> cachedData = cacheableConn.getCachedData("SELECT * FROM products");
                System.out.println("\nCached query results:");
                if (cachedData != null) {
                    ResultSetUtils.printCachedResultSet(cachedData);
                } else {
                    System.out.println("No cached data found for query: SELECT * FROM products");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbConnectionPool.returnConnectionBack(conn);
        }
    }


}
