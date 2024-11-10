package org.example.utils;

import org.example.domain.models.Order;
import org.example.domain.models.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderUtils {

    private OrderUtils() {
    }

    public static Order generateOrder() {
        return Order.builder()
                .setId(1L)
                .setDescription("Order description")
                .setProductList(generateProductList(100))
                .build();
    }

    public static Product generateProduct(Long id) {
        return Product.builder()
                .setId(id)
                .setName("Product" + id)
                .setPrice(BigDecimal.valueOf(12))
                .build();
    }

    private static List<Product> generateProductList(int amount) {
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            productList.add(generateProduct((long) i));
        }
        return productList;
    }
}
