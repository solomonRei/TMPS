package org.example.domain.observer;

import org.example.domain.loggerChain.LogLevel;
import org.example.domain.loggerChain.Logger;
import org.example.domain.loggerChain.LoggerChain;
import org.example.domain.models.Order;

public class InventoryObserver implements Observer {
    private final Logger logger = LoggerChain.getLoggerChain();

    @Override
    public void update(String eventType, Order order) {
        if ("productAdded".equals(eventType)) {
            logger.log("InventoryObserver: Product added for Order ID: " + order.getId(), LogLevel.INFO);
        }
    }
}

