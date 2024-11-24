package org.example.domain.observer;


import org.example.domain.loggerChain.LogLevel;
import org.example.domain.loggerChain.Logger;
import org.example.domain.loggerChain.LoggerChain;
import org.example.domain.models.Order;

public class NotificationObserver implements Observer {
    private final Logger logger = LoggerChain.getLoggerChain();

    @Override
    public void update(String eventType, Order order) {
        logger.log("NotificationObserver: Event '" + eventType + "' received for Order ID: " + order.getId(), LogLevel.INFO);
    }
}

