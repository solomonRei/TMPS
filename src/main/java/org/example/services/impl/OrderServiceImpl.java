package org.example.services.impl;

import org.example.domain.loggerChain.LogLevel;
import org.example.domain.loggerChain.Logger;
import org.example.domain.loggerChain.LoggerChain;
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
    private final Logger logger;

    public OrderServiceImpl(PaymentService paymentService, DiscountService discountService,
                            DBConnectionPool dbConnectionPool, TaxCalculator taxCalculator) {
        this.paymentService = paymentService;
        this.discountService = discountService;
        this.dbConnectionPool = dbConnectionPool;
        this.taxCalculator = taxCalculator;
        this.logger = LoggerChain.getLoggerChain();
    }

    public void processOrder(Order order) {
        // Just for demonstration purposes
        getProductList();

        BigDecimal amount = order.getProductList().stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        logger.log("\n----DISCOUNT CALCULATION ADAPTERS----", LogLevel.INFO);
        logger.log("Total amount before discounts: " + amount, LogLevel.INFO);

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
        logger.log("Final discounted amount after discounts: " + finalAmount, LogLevel.INFO);

        logger.log("\n----TAX CALCULATION----", LogLevel.INFO);
        BigDecimal tax = taxCalculator.calculateTax(finalAmount);
        BigDecimal totalWithTax = finalAmount.add(tax);
        logger.log("Tax: " + tax, LogLevel.INFO);
        logger.log("Total amount after tax: " + totalWithTax, LogLevel.INFO);

        logger.log("\n----PAYMENT----", LogLevel.INFO);
        paymentService.pay(totalWithTax);

        Order managerOrder = new Order(order, "Manager Invoice");
        Order userOrder = new Order(order, "User Invoice");

        logger.log("Original Order Description: " + order, LogLevel.INFO);
        logger.log("Manager Order Description: " + managerOrder, LogLevel.INFO);
        logger.log("User Order Description: " + userOrder, LogLevel.INFO);
    }

    private void getProductList() {
        DBConnection conn = dbConnectionPool.getConnection();

        logger.log("------ Getting product list from the database -----\n", LogLevel.INFO);

        try {
            ResultSet resultSet1 = conn.executeQuery("SELECT * FROM products");
            logger.log("\n----QUERY RESULTS----", LogLevel.DEBUG);
            if (resultSet1 != null) {
                List<Map<String, Object>> rows = ResultSetUtils.extractData(resultSet1);
                ResultSetUtils.printCachedResultSet(rows);
            }

            if (conn instanceof CacheableConnection cacheableConn) {
                List<Map<String, Object>> cachedData = cacheableConn.getCachedData("SELECT * FROM products");
                logger.log("\n----CACHED QUERY RESULTS----", LogLevel.DEBUG);
                if (cachedData != null) {
                    ResultSetUtils.printCachedResultSet(cachedData);
                } else {
                    logger.log("No cached data found for query: SELECT * FROM products", LogLevel.ERROR);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbConnectionPool.returnConnectionBack(conn);
        }
    }


}
