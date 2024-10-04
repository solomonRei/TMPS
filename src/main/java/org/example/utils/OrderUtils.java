package org.example.utils;

import org.example.Order;
import org.example.Product;

import java.math.BigDecimal;
import java.util.List;

public class OrderUtils {

    private OrderUtils() {
    }

    public static Order generateOrder() {
        Order order = new Order();
        order.setId(1L);
        order.setDescription("Order description");
        order.setProductList(List.of(generateProduct(1L), generateProduct(2L)));
        return order;
    }

    public static Product generateProduct(Long id) {
        Product product = new Product();
        product.setId(id);
        product.setName("Product" + id);
        product.setPrice(BigDecimal.valueOf(12));
        return product;
    }
}
