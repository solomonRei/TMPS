package org.example.domain.observer;

import org.example.domain.models.Order;

public interface Observer {
    void update(String eventType, Order order);
}
