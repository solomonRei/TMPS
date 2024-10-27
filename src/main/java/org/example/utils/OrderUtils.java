package org.example.utils;

import org.example.domain.models.Order;
import org.example.domain.models.Product;

import java.math.BigDecimal;
import java.util.List;

public class OrderUtils {

    private OrderUtils() {
    }

    public static Order generateOrder() {
        return Order.builder()
                .setId(1L)
                .setDescription("Order description")
                .setProductList(List.of(generateProduct(1L), generateProduct(2L)))
                .build();
    }

    public static Product generateProduct(Long id) {
        return Product.builder()
                .setId(id)
                .setName("Product" + id)
                .setPrice(BigDecimal.valueOf(12))
                .build();
    }
}
