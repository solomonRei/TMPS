package org.example.utils;

import org.example.domain.models.Order;
import org.example.services.InventoryService;
import org.example.services.NotificationService;
import org.example.services.OrderService;

public class OrderFacade {
    private final InventoryService inventoryService;
    private final OrderService orderService;
    private final NotificationService notificationService;

    public OrderFacade(InventoryService inventoryService, OrderService orderService,
                       NotificationService notificationService) {
        this.inventoryService = inventoryService;
        this.orderService = orderService;
        this.notificationService = notificationService;
    }

    public void placeOrder(Order order) {
        System.out.println("Placing order...");

        System.out.println("\n-----INVENTORY-----");
        // 1. check if product is in stock
        order.getProductList().stream()
                .filter(product -> !inventoryService.checkStock(product.getId()))
                .forEach(product -> {
                    System.out.println("Product is out of stock!");
                    notificationService.sendNotification("Product " + product.getName() + " is out of stock!");
                    throw new RuntimeException("Product " + product.getName() + " is out of stock!");
                });

        System.out.println("\n-----PROCESSING ORDER-----");
        // 2. process order
        orderService.processOrder(order);

        System.out.println("\n-----NOTIFICATION-----");
        // 3. notify user
        notificationService.sendNotification("Your order has been placed successfully!");

        System.out.println("Order placed successfully!");
    }
}

